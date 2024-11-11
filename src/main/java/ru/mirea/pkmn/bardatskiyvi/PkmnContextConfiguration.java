package ru.mirea.pkmn.bardatskiyvi;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseService;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceBuilder;

import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class PkmnContextConfiguration {

    @Bean
    @Scope("singleton")
    public DatabaseService databaseService() throws SQLException, IOException {
        return new DatabaseServiceBuilder().buildDatabaseService();
    }

    @Bean
    public StudentService studentService() throws SQLException, IOException {
        return new StudentService();
    }

    @Bean
    public CardService cardService() throws SQLException, IOException {
        return new CardService();
    }
}
