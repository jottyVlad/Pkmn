package ru.mirea.pkmn.bardatskiyvi.web.jdbc;

import com.google.gson.Gson;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;
import ru.mirea.pkmn.bardatskiyvi.AbstractFileAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.mirea.pkmn.bardatskiyvi.CardService.getCardFromResultSet;
import static ru.mirea.pkmn.bardatskiyvi.StudentService.resultSetToStudent;

public class DatabaseServiceImpl extends AbstractFileAction implements DatabaseService  {
    private final Connection connection;

    private final Properties databaseProperties;
    private final String PATH_TO_PROPERTIES = PATH_TO_RESOURCES + "database.properties";

    public DatabaseServiceImpl() throws SQLException, IOException {

        databaseProperties = new Properties();
        databaseProperties.load(new FileInputStream(PATH_TO_PROPERTIES));

        connection = DriverManager.getConnection(
                databaseProperties.getProperty("database.url"),
                databaseProperties.getProperty("database.user"),
                databaseProperties.getProperty("database.password")
        );

        if(connection.isValid(0))
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.INFO,
                    "Connected to database successfully");
        else
            Logger.getLogger(DatabaseServiceImpl.class.getName()).log(Level.WARNING,
                    "Error connecting to database");
    }

    public Card getCardFromDatabaseById(UUID uuid) throws SQLException {
        String query = "select * from card WHERE \"id\" = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                Card card = getCardFromResultSet(resultSet, this);
                return card;
            }
            else return null;
        }
    }

    public Student getStudentFromDatabaseById(UUID uuid) throws SQLException {
        String query = "select * from student WHERE \"id\" = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                return resultSetToStudent(resultSet);
            }
            else return null;
        }
    }

    @Override
    public Card  getCardFromDatabase(String cardName) throws SQLException {
        String query = "select * from card WHERE \"name\" = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardName);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next())
            {
                Card card = getCardFromResultSet(resultSet, this);
                statement.close();
                return card;
            }
            else return null;
        }
    }

    @Override
    public Student getStudentFromDatabase(String studentName) throws SQLException {
        String query = "select * from student where \"familyName\" = ? and" +
                " \"firstName\" = ? and" +
                " \"patronicName\" = ?";

        String[] splittedName = studentName.split(" ");
        if (splittedName.length != 3) {
            throw new IllegalArgumentException("You must provide 3 words separated by space in the string!");
        }

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, splittedName[0]);
            statement.setString(2, splittedName[1]);
            statement.setString(3, splittedName[2]);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return resultSetToStudent(resultSet);
            }
            return null;
        }
    }

    private UUID getCardIdFromDatabase(String cardName) throws SQLException {
        String query = "select * from card WHERE \"name\" = ?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardName);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return UUID.fromString(resultSet.getString("id"));
            }
            throw new IllegalArgumentException("No card with provided name found!");
        }
    }

    private UUID getStudentIdFromDatabase(String studentName) throws SQLException {
        String query = "select * from student where \"familyName\" = ? and" +
                " \"firstName\" = ? and" +
                " \"patronicName\" = ?";

        String[] splittedName = studentName.split(" ");
        if (splittedName.length != 3) {
            throw new IllegalArgumentException("You must provide 3 words separated by space in the string!");
        }

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, splittedName[0]);
            statement.setString(2, splittedName[1]);
            statement.setString(3, splittedName[2]);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return UUID.fromString(resultSet.getString("id"));
            }
            return null;
        }
    }

    @Override
    public void saveCardToDatabase(Card card) throws SQLException {
        if(getCardFromDatabase(card.getName()) != null) {
            return;
        }

        Card evolvesFrom;
        UUID evolvesFromId = null;
        if((evolvesFrom = card.getEvolvesFrom()) != null) {
            if(getCardFromDatabase(evolvesFrom.getName()) == null) {
                saveCardToDatabase(evolvesFrom);
            }
            evolvesFromId = getCardIdFromDatabase(evolvesFrom.getName());
        }


        Student pokemonOwner;
        UUID ownerId = null;
        if((pokemonOwner = card.getPokemonOwner()) != null) {
            if(getStudentFromDatabase(
                    pokemonOwner.getSurName() + " " +
                            pokemonOwner.getFirstName() + " " +
                            pokemonOwner.getFamilyName()
            ) == null) {
                createPokemonOwner(card.getPokemonOwner());
            }
            ownerId = getStudentIdFromDatabase(pokemonOwner.getSurName() + " " +
                    pokemonOwner.getFirstName() + " " +
                    pokemonOwner.getFamilyName());
        }


        String query = "insert into card(id, name, hp, evolves_from, " +
                "game_set, pokemon_owner, stage, retreat_cost, " +
                "weakness_type, resistance_type, attack_skills, " +
                "pokemon_type, regulation_mark, card_number) VALUES(" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?::json, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, card.getName());
            statement.setInt(3, card.getHp());
            statement.setObject(4, evolvesFromId);
            statement.setString(5, card.getGameSet());
            statement.setObject(6, ownerId);
            statement.setString(7, card.getPokemonStage().name());
            statement.setString(8, card.getRetreatCost());

            if(card.getWeaknessType() != null) {
                statement.setString(9, card.getWeaknessType().name());
            } else {
                statement.setString(9, null);
            }

            if(card.getResistanceType() != null) {
                statement.setString(10, card.getResistanceType().name());
            } else {
                statement.setString(10, null);
            }

            statement.setString(11, new Gson().toJson(card.getSkills()));
            statement.setString(12, card.getPokemonType().name());
            statement.setString(13, String.valueOf(card.getRegulationMark()));
            statement.setString(14, card.getNumber());

            statement.execute();
        }
    }

    @Override
    public void createPokemonOwner(Student owner) throws SQLException {
        if(getStudentIdFromDatabase(owner.getSurName() + " " +
                owner.getFirstName() + " " + owner.getFamilyName()) != null) {
            return;
        }
        String query = "insert into student(id, " +
                "\"familyName\", \"firstName\", \"patronicName\", \"group\") " +
                "values(?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, owner.getSurName());
            statement.setString(3, owner.getFirstName());
            statement.setString(4, owner.getFamilyName());
            statement.setString(5, owner.getGroup());

            statement.execute();
        }
    }

}
