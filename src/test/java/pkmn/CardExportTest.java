package pkmn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

class CardExportTest {

    void assertCardFields(Card card) {
        Assertions.assertEquals(card.getEnergyType(), EnergyType.FIRE);
        Assertions.assertEquals(card.getName(), "Pyroar");
        Assertions.assertEquals(card.getGameSet(), "Scarlet & Violet—Paldea Evolved");
        Assertions.assertEquals(card.getHp(), 120);
        Assertions.assertEquals(card.getPokemonStage(), PokemonStage.STAGE1);
        Assertions.assertEquals(card.getRegulationMark(), 'G');
        Assertions.assertEquals(card.getWeaknessType(), EnergyType.WATER);
        Assertions.assertNull(card.getResistanceType());
        Assertions.assertEquals(card.getRetreatCost(), "2");

        ArrayList<AttackSkill> skills = new ArrayList<>();
        skills.add(new AttackSkill("Singe", "F", 0));
        skills.add(new AttackSkill("Overrun", "2", 60));
        Assertions.assertEquals(card.getSkills().toString(), skills.toString());

        Student owner = new Student("Vladislav", "Bardatskiy", "Igorevich", "BSBO-04-23");
        Assertions.assertEquals(card.getPokemonOwner().toString(), owner.toString());

        Assertions.assertEquals(card.getEvolvesFrom().getName(), "Litleo");
    }

    @Test
    void serializeToBytes() throws IOException, ClassNotFoundException {
        Card card = CardImport.importCardFromFile("my_card.txt");

        CardExport.serializeToBytes(card);
        card = CardImport.deserializeCardFromBytes(card.getName());

        assertCardFields(card);
    }

    @Test
    void testSerializeToBytes() throws IOException, ClassNotFoundException {
        Card card = CardImport.importCardFromFile("my_card.txt");
        String name = "name";

        CardExport.serializeToBytes(card, name);
        card = CardImport.deserializeCardFromBytes(name);

        assertCardFields(card);
    }
}