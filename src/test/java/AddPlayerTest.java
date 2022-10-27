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
        game.addPlayer("pippo");
        assertTrue(game.getPlayers().contains(new Player("pippo")));
        assertEquals(1, game.getPlayers().size());
        game.addPlayer("pluto");
        assertNotNull(game.getPlayer("pluto"));
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    @DisplayName("Test not adding existing players")
    public void testAddExistingPlayers() {
        System.out.println(game.getPlayers().size());
        game.addPlayer("pippo");
        assertEquals(1, game.getPlayers().size());
        game.addPlayer("pluto");
        assertEquals(2, game.getPlayers().size());
        game.addPlayer("pluto");
        assertEquals(2, game.getPlayers().size());
    }

}
