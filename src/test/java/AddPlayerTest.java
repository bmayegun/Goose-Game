import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddPlayerTest {

    private final Game game = new Game();

    @BeforeEach
    public void setup() {
        game.removeAllPlayers();
    }

    @Test
    @DisplayName("Test adding players")
    public void testAddPlayers() {
        Player player = new Player("pippo");
        game.addPlayer(player);
        assertTrue(game.getPlayers().contains(player));
        assertEquals(1, game.getPlayers().size());
        game.addPlayer("pluto");
        assertNotNull(game.getPlayer("pluto"));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    @DisplayName("Test not adding existing players")
    public void testAddExistingPlayers() {
//        game.addPlayer("pippo");
//        assertEquals(1, game.getPlayers().size());
//        game.addPlayer("pluto");
//        assertEquals(2, game.getPlayers().size());
//        game.addPlayer("pluto");
//        assertEquals(2, game.getPlayers().size());
    }

}
