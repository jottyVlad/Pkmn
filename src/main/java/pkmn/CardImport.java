package pkmn;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardImport extends AbstractFileAction {

    /**
     * Imports a card from a file with the given filename. The file is expected to
     * contain card information that will be parsed and used to create and populate
     * a new Card object.
     *
     * @param filename The name of the file to import the card data from.
     * @return The populated Card object created based on the input file content.
     * @throws IOException If there is an error reading from the file.
     */
    public static Card importCardFromFile(String filename) throws IOException {

        FileInputStream inputStream = new FileInputStream(PATH_TO_RESOURCES + filename);

        Card card = new Card();

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(inputStreamReader))
        {
            String line;

            while ((line = br.readLine()) != null) {
                processLine(line, card);
            }
        }

        return card;
    }

    /**
     * Processes a single line of the card input file and updates the corresponding fields
     * of the provided Card object based on the content of the line.
     *
     * @param line The input line from the card file, representing a specific attribute of a Card object.
     * @param card The Card object to update based on the parsed data from the input line.
     * @throws IOException If there is an error reading from the card file.
     */
    private static void processLine(String line, Card card) throws IOException {
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "1.":
                card.setPokemonStage(PokemonStage.valueOf(parts[1]));
                break;
            case "2.":
                card.setName(parts[1]);
                break;
            case "3.":
                card.setHp(Integer.parseInt(parts[1]));
                break;
            case "4.":
                card.setEnergyType(EnergyType.valueOf(parts[1]));
                break;
            case "5.":
                if (parts.length == 2 && !parts[1].isEmpty() && !parts[1].equals("-")) {
                    Card evolvesCard = importCardFromFile(parts[1]);
                    card.setEvolvesFrom(evolvesCard);
                } else {
                    card.setEvolvesFrom(null);
                }
                break;
            case "6.":
                List<AttackSkill> attackSkills = new ArrayList<>();
                if (parts[1].contains(",")) {
                    String[] skills = parts[1].split(",");
                    for (String skill : skills) {
                        String[] skillParts = skill.split("/");
                        attackSkills.add(new AttackSkill(skillParts[1], skillParts[0], Integer.parseInt(skillParts[2])));
                    }
                }
                if(parts[1].equals(" - ")) {
                    card.setSkills(null);
                } else {
                    card.setSkills(attackSkills);
                }
                break;
            case "7.":
                card.setWeaknessType(parts.length == 2 && !parts[1].isEmpty() ? EnergyType.valueOf(parts[1]) : null);
                break;
            case "8.":
                card.setResistanceType(parts.length == 2 && !parts[1].isEmpty() && !parts[1].equals("-") ? EnergyType.valueOf(parts[1]) : null);
                break;
            case "9.":
                card.setRetreatCost(parts.length == 2 && !parts[1].isEmpty() && !parts[1].equals("-") ? parts[1] : null);
                break;
            case "10.":
                card.setGameSet(parts.length == 2 &&
                        !parts[1].isEmpty() &&
                        !parts[1].equals("-")
                        ? null
                        : String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)));
                break;
            case "11.":
                card.setRegulationMark(parts.length == 2 && !parts[1].isEmpty() ? parts[1].charAt(0) : null);
                break;
            case "12.":
                if (parts.length == 2) {
                    String[] ownerInfo = parts[1].split("/");
                    Student owner = new Student(ownerInfo[1], ownerInfo[0], ownerInfo[2], ownerInfo[3]);
                    card.setPokemonOwner(owner);
                } else {
                    card.setPokemonOwner(null);
                }
                break;
        }
    }

    /**
     * Deserializes a Card object from a file of bytes based on the given name.
     *
     * @param name The name of the file (without the extension) to deserialize the Card object from.
     * @return The deserialized Card object.
     * @throws IOException If there is an error reading the file.
     * @throws ClassNotFoundException If the Card class is not found during deserialization.
     */
    public static Card deserializeCardFromBytes(String name) throws IOException, ClassNotFoundException {
        String path = PATH_TO_RESOURCES + name + ".crd";

        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (Card) objectInputStream.readObject();
    }
}
