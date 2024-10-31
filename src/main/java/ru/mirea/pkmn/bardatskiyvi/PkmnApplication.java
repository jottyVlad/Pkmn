package ru.mirea.pkmn.bardatskiyvi;

import com.fasterxml.jackson.databind.JsonNode;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.Student;
import ru.mirea.pkmn.bardatskiyvi.web.http.PkmnHttpClient;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceBuilder;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceImpl;

import java.io.IOException;
import java.sql.SQLException;


public class PkmnApplication {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DatabaseServiceImpl db = new DatabaseServiceBuilder()
                .buildDatabaseService();


        Card card = CardImport.importCardFromFile("my_card.txt");

        PkmnHttpClient httpClient = new PkmnHttpClient();
        JsonNode cardNode = httpClient.getPokemonCard("Pyroar", "32");

        card = CardImport.setDescriptionsFromAPI(card, httpClient);
        CardExport.serializeToBytes(card);

        card = CardImport.deserializeCardFromBytes(card.getName());

        System.out.println(cardNode.toPrettyString());
        System.out.println("\n\n");
        System.out.println(card);

        db.saveCardToDatabase(card);
        Card card1 = db.getCardFromDatabase("Pyroar");
        System.out.println(card1);
    }
}

