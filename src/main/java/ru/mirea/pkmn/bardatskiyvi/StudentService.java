package ru.mirea.pkmn.bardatskiyvi;

import ru.mirea.pkmn.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
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
}
