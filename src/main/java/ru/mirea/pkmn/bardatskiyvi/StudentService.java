package ru.mirea.pkmn.bardatskiyvi;

import org.springframework.stereotype.Component;
import ru.mirea.pkmn.Student;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


/**
 * A utility class that provides methods for working with Student objects and database results.
 */
@Component
public class StudentService {

    DatabaseService databaseService;
    void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public StudentService() { }

    /**
     * Creates a Student object from the current row of a ResultSet.
     *
     * @param resultSet The ResultSet containing the student data.
     * @return A Student object populated with data from the ResultSet.
     * @throws SQLException If a database access error occurs.
     */
    public static Student resultSetToStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        String value;
        if((value = resultSet.getString("firstName")) != null) {
            student.setFirstName(value);
        } else student.setFirstName(null);

        if((value = resultSet.getString("familyName")) != null) {
            student.setSurName(value);
        } else student.setSurName(null);

        if((value = resultSet.getString("patronicName")) != null) {
            student.setFamilyName(value);
        } else student.setFamilyName(null);

        if((value = resultSet.getString("group")) != null) {
            student.setGroup(value);
        } else student.setGroup(null);

        return student;
    }


    /**
     * Retrieves a Student object from the database based on their unique identifier (UUID).
     *
     * @param uuid The UUID of the student to retrieve.
     * @return The Student object corresponding to the provided UUID, or null if no such student exists in the database.
     * @throws SQLException If an error occurs during the database interaction.
     */
    public Student getStudentFromDatabaseById(UUID uuid) throws SQLException {
        ResultSet resultSet = databaseService.getStudentFromDatabaseById(uuid);

        if(resultSet.next())
        {
            return resultSetToStudent(resultSet);
        }
        else return null;
    }
}
