package ru.mirea.pkmn;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Represents a Pokémon card in a trading card game.
 * This class holds various attributes related to the Pokémon card, including
 * its stage, name, hp, energy type, evolution information, attack skills, weakness, resistance,
 * retreat cost, game set, regulation mark, and its owner.
 */
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Represents the evolution stage of a Pokémon card.
     * It can be BASIC, STAGE1, STAGE2, VSTAR, or VMAX.
     */
    private PokemonStage pokemonStage;
    public PokemonStage getPokemonStage() {
        return pokemonStage;
    }
    public void setPokemonStage(PokemonStage value) {
        pokemonStage = value;
    }


    public Card() {
    }


    /**
     * The name of the Pokémon card.
     */
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Represents the hp of a Pokémon card.
     */
    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int value) {
        hp = value;
    }

    /**
     * Represents the energy type associated with a Pokémon card.
     *
     * @see EnergyType
     */
    private EnergyType pokemonType;
    public EnergyType getPokemonType() {
        return pokemonType;
    }
    public void setPokemonType(EnergyType value) {
        pokemonType = value;
    }

    /**
     * Represents the previous evolutionary stage of the current Pokémon.
     * This variable holds a reference to the Card instance that the current Pokémon
     * evolves from, if applicable. If the Pokémon is a basic Pokémon or does not have
     * a previous stage, this will be null.
     */
    private Card evolvesFrom;
    public Card getEvolvesFrom() {
        return evolvesFrom;
    }
    public void setEvolvesFrom(Card value) {
        evolvesFrom = value;
    }

    /**
     * A list of attack skills that the card possesses.
     */
    private List<AttackSkill> skills;
    public List<AttackSkill> getSkills() {
        return skills;
    }
    public void setSkills(List<AttackSkill> values) {
        skills = values;
    }

    /**
     * Represents the type of energy this Pokémon card is weak against.
     * This field indicates which kind of attacks do extra damage to this Pokémon.
     */
    private EnergyType weaknessType;
    public EnergyType getWeaknessType() {
        return weaknessType;
    }
    public void setWeaknessType(EnergyType value) {
        weaknessType = value;
    }

    /**
     * Represents the type of energy the card has resistance against.
     */
    private EnergyType resistanceType;
    public EnergyType getResistanceType() {
        return resistanceType;
    }
    public void setResistanceType(EnergyType value) {
        resistanceType = value;
    }

    /**
     * The cost required for a Pokémon to retreat from battle.
     * This is typically represented in terms of energy points needed.
     */
    private String retreatCost;
    public String getRetreatCost() {
        return retreatCost;
    }
    public void setRetreatCost(String value) {
        retreatCost = value;
    }

    /**
     * The gameSet variable represents the specific set to which a Pokémon card belongs in a collectible card game.
     * This field is used to categorize and identify the card within the game’s various series and expansions.
     */
    private String gameSet;
    public String getGameSet() {
        return gameSet;
    }
    public void setGameSet(String value) {
        gameSet = value;
    }

    /**
     * Represents the regulation mark associated with a Pokémon card.
     * This mark designates the legal usage period or generation of the card in tournament play.
     */
    private char regulationMark;
    public Character getRegulationMark() {
        return regulationMark;
    }
    public void setRegulationMark(Character value) {
        regulationMark = value;
    }

    /**
     * Represents the student who owns the Pokémon card.
     */
    private Student pokemonOwner;
    public Student getPokemonOwner() {
        return pokemonOwner;
    }
    public void setPokemonOwner(Student value) {
        pokemonOwner = value;
    }


    @Override
    public String toString() {
//        return "1. " + pokemonStage + "\n" +
//                "2. " + name + "\n" +
//                "3. " + hp + "\n" +
//                "4. " + energyType + "\n" +
//                "5. " + evolvesFrom + "\n" +
//                "6. " + skills + "\n" +
//                "7. " + weaknessType + "\n" +
//                "8. " + resistanceType + "\n" +
//                "9. " + retreatCost + "\n" +
//                "10. " + gameSet + "\n" +
//                "11. " + regulationMark + "\n" +
//                "12. " + pokemonOwner;
        return "Stage: " + pokemonStage + '\n' +
                "Name: " + name + '\n' +
                "HP: " + hp + '\n' +
                "Energy Type: " + pokemonType + '\n' +
                "Evolves from: " + (evolvesFrom != null ? "\n" + evolvesFrom : "none") + "\n\n" +
                "Skills: " + skills + '\n' +
                "Weakness type: " + weaknessType + '\n' +
                "Resistance type: " + resistanceType + '\n' +
                "Retreat cost: " + retreatCost + '\n' +
                "Game set: " + gameSet + '\n' +
                "Regulation mark: " + regulationMark + '\n' +
                "Pokemon owner: " + (pokemonOwner != null ? pokemonOwner.toString() : "no owner");
    }
}
