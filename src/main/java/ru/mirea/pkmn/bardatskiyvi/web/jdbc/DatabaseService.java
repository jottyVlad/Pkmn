package ru.mirea.pkmn.bardatskiyvi.web.jdbc;

import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;

import java.sql.SQLException;

public interface DatabaseService {
    Card getCardFromDatabase(String cardName) throws SQLException;
    Student getStudentFromDatabase(String studentFullName) throws SQLException;
    void saveCardToDatabase(Card card) throws SQLException;
    void createPokemonOwner(Student owner) throws SQLException;
}
