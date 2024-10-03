package pkmn;

import java.io.IOException;

public class PkmnApplication {
    public static void main(String[] args) throws IOException {
        Card card = CardImport.importCardFromFile("my_card.txt");
    }
}
