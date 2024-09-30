package pkmn;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardImport {

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

    public static Card importCardFromFile(String filename) throws IOException, ClassNotFoundException, URISyntaxException {
//        String url = CardImport.class.getResource(filename).getFile();
//        Path path = Paths.get(url.toURI());
//        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
//        System.out.println(lines.get(0));

        CardImport instance
                = new CardImport();

        InputStream is = instance.getFileAsIOStream(filename);
        instance.printFileContent(is);

        is = instance.getFileAsIOStream(filename);

        Card card = new Card();

        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);)
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" ");
                switch (splitted[0]) {
                    case "1.":
                        card.setPokemonStage(PokemonStage.valueOf(splitted[1]));
                    case "2.":
                        card.setName(splitted[1]);
                    case "3.":
                        card.setHp(Integer.valueOf(splitted[1]));
                    case "4.":
                        card.setEnergyType(EnergyType.valueOf(splitted[1]));
                    case "5.":
                        if(!Objects.equals(splitted[1], "")) {
                            card.setEvolvesFrom(importCardFromFile(splitted[1]));
                        }
                    case "6.":
                        ArrayList<AttackSkill> attackSkills = new ArrayList<AttackSkill>();
                        if(splitted[1].contains(",")) {
                            String[] attackSkillsStrings = splitted[1].split(",");
                            for(String i : attackSkillsStrings) {
                                String[] skillSplited = i.split("/");
                                attackSkills.add(new AttackSkill(skillSplited[0], skillSplited[1], Integer.parseInt(skillSplited[2])));
                            }
                        }
                        card.setSkills(attackSkills);
                    case "7.":
                        if(!Objects.equals(splitted[1], "")) {
                            card.setWeaknessType(EnergyType.valueOf(splitted[1]));
                        } else {
                            card.setWeaknessType(null);
                        }
                    case "8.":
                        if(!Objects.equals(splitted[1], "")) {
                            card.setResistanceType(EnergyType.valueOf(splitted[1]));
                        } else {
                            card.setResistanceType(null);
                        }
                    case "9.":
                        card.setRetreatCost(splitted[1]);
                    case "10.":
                        card.setGameSet(splitted[1]);
                    case "11.":
                        card.setRegulationMark(splitted[1].charAt(0));

                }
            }
            is.close();
        }

        return card;
    }

    private void printFileContent(InputStream is) throws IOException
    {
        try (InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);)
        {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            is.close();
        }
    }
}
