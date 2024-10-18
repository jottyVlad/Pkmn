package ru.mirea.pkmn.bardatskiyvi;

import ru.mirea.pkmn.Card;

import java.io.IOException;

public class PkmnApplication {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Card card = CardImport.importCardFromFile("my_card.txt");

        CardExport.serializeToBytes(card);

        Card card1 = CardImport.deserializeCardFromBytes("Pyroar");
        System.out.println(card1.getEvolvesFrom());
    }
}

