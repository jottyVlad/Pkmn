package ru.mirea.pkmn.bardatskiyvi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import ru.mirea.pkmn.AttackSkill;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.EnergyType;
import ru.mirea.pkmn.PokemonStage;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseService;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceImpl;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * A utility class that provides methods for working with Card objects and database results.
 */
@Component
public class CardService {

    private DatabaseService databaseService;
    public void setDatabaseService(DatabaseService databaseService) { // TODO: do it by Builder and make it private
        this.databaseService = databaseService;
    }


    private StudentService studentService;
    void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public CardService() { }

    /**
     * Creates a Card object from the current row of a ResultSet, retrieving related data from the database.
     *
     * @param resultSet The ResultSet containing the card data.
     * @return A Card object populated with data from the ResultSet and related tables.
     * @throws SQLException If a database access error occurs.
     */
    public Card getCardFromResultSet(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setName(resultSet.getString("name"));
        card.setHp(resultSet.getInt("hp"));
        
        if(resultSet.getString("evolves_from") != null) {
            Card evolvesFromCard = getCardFromDatabaseById(
                    UUID.fromString(resultSet.getString("evolves_from"))
            );
            card.setEvolvesFrom(evolvesFromCard);
            card.getEvolvesFrom().setTabulation(card.getTabulation() + 1);
        } else {
            card.setEvolvesFrom(null);
        }

        card.setGameSet(resultSet.getString("game_set"));

        card.setPokemonOwner(
                resultSet.getString("pokemon_owner") != null ?
                        studentService.getStudentFromDatabaseById(
                                UUID.fromString(resultSet.getString("pokemon_owner"))
                        ) : null
        );


        card.setPokemonStage(PokemonStage.valueOf(resultSet.getString("stage")));

        String value;
        card.setRetreatCost((value = resultSet.getString("retreat_cost")) != null ? value : null);
        card.setWeaknessType((value = resultSet.getString("weakness_type")) != null ? EnergyType.valueOf(value) : null);
        card.setResistanceType((value = resultSet.getString("resistance_type")) != null ? EnergyType.valueOf(value) : null);

        Gson gson = new Gson();
        Type type = new TypeToken<List<AttackSkill>>() {}.getType();
        List<AttackSkill> skills = gson.fromJson(resultSet.getString("attack_skills"), type);

        card.setSkills(skills);
        card.setPokemonType(EnergyType.valueOf(resultSet.getString("pokemon_type")));
        card.setRegulationMark((value = resultSet.getString("regulation_mark")) != null ? value.charAt(0) : null);
        card.setNumber(resultSet.getString("card_number"));
        return card;
    }



    /**
     * Retrieves a Card object from the database based on its unique identifier (UUID).
     *
     * @param uuid The UUID of the card to retrieve.
     * @return The Card object corresponding to the provided UUID, or null if no such card exists in the database.
     * @throws SQLException If an error occurs during the database interaction.
     */
    public Card getCardFromDatabaseById(UUID uuid) throws SQLException {
        ResultSet resultSet = databaseService.getCardFromDatabaseById(uuid);

        if(resultSet.next())
        {
            return getCardFromResultSet(resultSet);
        }
        else return null;
    }


    public Card getCardFromDatabase(String cardName) throws SQLException {
        ResultSet resultSet = databaseService.getCardFromDatabase(cardName);

        if(resultSet.next())
        {
            return getCardFromResultSet(resultSet);
        }
        else return null;
    }

}
