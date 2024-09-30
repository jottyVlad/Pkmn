package pkmn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class PkmnApplication {
    public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException {
        Card card = CardImport.importCardFromFile("my_card.txt");
        System.out.println(card.getName());
    }
}
