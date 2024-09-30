package pkmn;

import java.io.Serializable;
import java.util.List;

public class Card implements Serializable {
    private PokemonStage pokemonStage;

    public Card() {
    }

    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }
    public void setPokemonStage(PokemonStage value) {
        pokemonStage = value;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int value) {
        hp = value;
    }

    private EnergyType energyType;
    public EnergyType getEnergyType() {
        return energyType;
    }
    public void setEnergyType(EnergyType value) {
        energyType = value;
    }

    private Card evolvesFrom;
    public Card getEvolvesFrom() {
        return evolvesFrom;
    }
    public void setEvolvesFrom(Card value) {
        evolvesFrom = value;
    }

    private List<AttackSkill> skills;
    public List<AttackSkill> getSkills() {
        return skills;
    }
    public void setSkills(List<AttackSkill> values) {
        skills = values;
    }

    private EnergyType weaknessType;
    public EnergyType getWeaknessType() {
        return weaknessType;
    }
    public void setWeaknessType(EnergyType value) {
        weaknessType = value;
    }

    private EnergyType resistanceType;
    public EnergyType getResistanceType() {
        return resistanceType;
    }
    public void setResistanceType(EnergyType value) {
        resistanceType = value;
    }

    private String retreatCost;
    public String getRetreatCost() {
        return retreatCost;
    }
    public void setRetreatCost(String value) {
        retreatCost = value;
    }

    private String gameSet;
    public String getGameSet() {
        return gameSet;
    }
    public void setGameSet(String value) {
        gameSet = value;
    }

    private char regulationMark;
    public char getRegulationMark() {
        return regulationMark;
    }
    public void setRegulationMark(char value) {
        regulationMark = value;
    }

    private Student pokemonOwner;
    public Student getPokemonOwner() {
        return pokemonOwner;
    }
    public void setPokemonOwner(Student value) {
        pokemonOwner = value;
    }


    @Override
    public String toString() {
        return "1. " + pokemonStage + "\n" +
                "2. " + name + "\n" +
                "3. " + hp + "\n" +
                "4. " + energyType + "\n" +
                "5. " + evolvesFrom + "\n" +
                "6. " + skills + "\n" +
                "7. " + weaknessType + "\n" +
                "8. " + resistanceType + "\n" +
                "9. " + retreatCost + "\n" +
                "10. " + gameSet + "\n" +
                "11. " + regulationMark + "\n" +
                "12. " + pokemonOwner;
//        return "Card{" +
//                "pokemonStage=" + pokemonStage +
//                ", name='" + name + '\'' +
//                ", hp=" + hp +
//                ", energyType=" + energyType +
//                ", evolvesFrom=" + evolvesFrom +
//                ", skills=" + skills +
//                ", weaknessType=" + weaknessType +
//                ", resistanceType=" + resistanceType +
//                ", retreatCost='" + retreatCost + '\'' +
//                ", gameSet='" + gameSet + '\'' +
//                ", regulationMark=" + regulationMark +
//                ", pokemonOwner=" + pokemonOwner +
//                '}';
    }
}
