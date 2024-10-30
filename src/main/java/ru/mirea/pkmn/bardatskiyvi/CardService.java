package ru.mirea.pkmn.bardatskiyvi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.mirea.pkmn.AttackSkill;
import ru.mirea.pkmn.Card;
import ru.mirea.pkmn.EnergyType;
import ru.mirea.pkmn.PokemonStage;
import ru.mirea.pkmn.bardatskiyvi.web.jdbc.DatabaseServiceImpl;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CardService {
    public static Card getCardFromResultSet(ResultSet resultSet,
                                            DatabaseServiceImpl db) throws SQLException {
        Card card = new Card();
        card.setName(resultSet.getString("name"));
        card.setHp(resultSet.getInt("hp"));

        if(resultSet.getString("evolves_from") != null) {
            Card evolvesFromCard = db.getCardFromDatabaseById(
                    UUID.fromString(resultSet.getString("evolves_from"))
            );
            card.setEvolvesFrom(evolvesFromCard);
            card.getEvolvesFrom().setTabulation(card.getTabulation() + 1);
        } else {
            card.setEvolvesFrom(null);
        }

        card.setGameSet(resultSet.getString("game_set"));

        if(resultSet.getString("pokemon_owner") != null) {
            card.setPokemonOwner(
                    db.getStudentFromDatabaseById(
                            UUID.fromString(resultSet.getString("pokemon_owner"))
                    )
            );
        } else
            card.setPokemonOwner(null);


        card.setPokemonStage(PokemonStage.valueOf(resultSet.getString("stage")));

        String value;
        if((value = resultSet.getString("retreat_cost")) != null) {
            card.setRetreatCost(value);
        } else card.setRetreatCost(null);

        if((value = resultSet.getString("weakness_type")) != null) {
            card.setWeaknessType(EnergyType.valueOf(value));
        } else card.setWeaknessType(null);

        if((value = resultSet.getString("resistance_type")) != null) {
            card.setResistanceType(EnergyType.valueOf(value));
        } else card.setResistanceType(null);

        Gson gson = new Gson();
        Type type = new TypeToken<List<AttackSkill>>() {}.getType();
        List<AttackSkill> skills = gson.fromJson(resultSet.getString("attack_skills"), type);
        card.setSkills(skills);

        card.setPokemonType(EnergyType.valueOf(resultSet.getString("pokemon_type")));

        if((value = resultSet.getString("regulation_mark")) != null) {
            card.setRegulationMark(value.charAt(0));
        } else card.setRegulationMark(null);

        card.setNumber(resultSet.getString("card_number"));
        return card;
    }
}
