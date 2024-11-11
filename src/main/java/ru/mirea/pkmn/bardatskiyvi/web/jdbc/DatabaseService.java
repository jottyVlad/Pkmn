package ru.mirea.pkmn.bardatskiyvi.web.jdbc;

import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface DatabaseService {
    ResultSet getCardFromDatabase(String cardName) throws SQLException;
    Student getStudentFromDatabase(String studentFullName) throws SQLException;
    void saveCardToDatabase(Card card) throws SQLException;
    void createPokemonOwner(Student owner) throws SQLException;
    ResultSet getCardFromDatabaseById(UUID id) throws SQLException;
    ResultSet getStudentFromDatabaseById(UUID id) throws SQLException;
}
