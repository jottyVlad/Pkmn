package pkmn;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.mirea.pkmn.bardatskiyvi.web.http.PkmnHttpClient;

import java.io.IOException;

public class HttpClientTest {

    @Test
    void testHttpClient() throws IOException {
        PkmnHttpClient httpClient = new PkmnHttpClient();

        final String result = "{\"data\":[{\"id\":\"sv2-32\",\"name\":\"Pyroar\",\"supertype\":\"Pokémon\",\"subtypes\":[\"Stage 1\"],\"hp\":\"120\",\"types\":[\"Fire\"],\"evolvesFrom\":\"Litleo\",\"attacks\":[{\"cost\":[\"Fire\"],\"name\":\"Singe\",\"damage\":\"\",\"text\":\"Your opponent's Active Pokémon is now Burned.\",\"convertedEnergyCost\":1},{\"cost\":[\"Colorless\",\"Colorless\"],\"name\":\"Overrun\",\"damage\":\"60\",\"text\":\"This attack also does 20 damage to 1 of your opponent's Benched Pokémon. (Don't apply Weakness and Resistance for Benched Pokémon.)\",\"convertedEnergyCost\":2}],\"weaknesses\":[{\"type\":\"Water\",\"value\":\"×2\"}],\"retreatCost\":[\"Colorless\",\"Colorless\"],\"convertedRetreatCost\":2,\"set\":{\"id\":\"sv2\",\"name\":\"Paldea Evolved\",\"series\":\"Scarlet & Violet\",\"printedTotal\":193,\"total\":279,\"legalities\":{\"unlimited\":\"Legal\",\"standard\":\"Legal\",\"expanded\":\"Legal\"},\"releaseDate\":\"2023/06/09\",\"updatedAt\":\"2023/06/09 15:00:00\",\"images\":{\"symbol\":\"https://images.pokemontcg.io/sv2/symbol.png\",\"logo\":\"https://images.pokemontcg.io/sv2/logo.png\"}},\"number\":\"32\",\"artist\":\"Uta\",\"rarity\":\"Uncommon\",\"flavorText\":\"The females of a pride work together to bring down prey. It's thanks to them that their pride doesn't starve.\",\"nationalPokedexNumbers\":[668],\"legalities\":{\"unlimited\":\"Legal\",\"standard\":\"Legal\",\"expanded\":\"Legal\"},\"regulationMark\":\"G\",\"images\":{\"small\":\"https://images.pokemontcg.io/sv2/32.png\",\"large\":\"https://images.pokemontcg.io/sv2/32_hires.png\"},\"tcgplayer\":{\"url\":\"https://prices.pokemontcg.io/tcgplayer/sv2-32\",\"updatedAt\":\"2024/10/31\",\"prices\":{\"normal\":{\"low\":0.01,\"mid\":0.1,\"high\":1.27,\"market\":0.04,\"directLow\":0.02},\"reverseHolofoil\":{\"low\":0.02,\"mid\":0.15,\"high\":15.0,\"market\":0.11,\"directLow\":0.06}}},\"cardmarket\":{\"url\":\"https://prices.pokemontcg.io/cardmarket/sv2-32\",\"updatedAt\":\"2024/10/31\",\"prices\":{\"averageSellPrice\":0.04,\"lowPrice\":0.02,\"trendPrice\":0.03,\"germanProLow\":0.0,\"suggestedPrice\":0.0,\"reverseHoloSell\":0.15,\"reverseHoloLow\":0.02,\"reverseHoloTrend\":0.16,\"lowPriceExPlus\":0.02,\"avg1\":0.02,\"avg7\":0.05,\"avg30\":0.04,\"reverseHoloAvg1\":0.1,\"reverseHoloAvg7\":0.1,\"reverseHoloAvg30\":0.13}}}],\"page\":1,\"pageSize\":250,\"count\":1,\"totalCount\":1}";

        JsonNode resultClient = httpClient.getPokemonCard("Pyroar", "32");
        Assertions.assertEquals(result, resultClient.toString());
    }
}
