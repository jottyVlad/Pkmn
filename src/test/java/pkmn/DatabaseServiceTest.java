package pkmn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.bardatskiyvi.CardService;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceBuilder;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;


public class DatabaseServiceTest {

    DatabaseServiceImpl db;
    CardService cardService;


    public DatabaseServiceTest() throws SQLException, IOException {
        this.cardService = new CardService();
        this.db = new DatabaseServiceBuilder()
                .setPropertiesFile("database_test.properties")
                .buildDatabaseService();
        this.cardService.setDatabaseService(db);
    }

    @Test
    public void testGetCardFromDatabaseById_Success() throws SQLException {

        Card card = cardService.getCardFromDatabaseById(
                UUID.fromString("93c413ca-dba2-4160-afca-02f0875bc086"));
        Assertions.assertNotNull(card);
    }

    @Test
    public void testGetCardFromDatabaseById_Fail() throws SQLException {
        Card card = cardService
                .getCardFromDatabaseById(
                        UUID.fromString("00000000-0000-0000-0000-000000000000"));
        Assertions.assertNull(card);
    }
}
