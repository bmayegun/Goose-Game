import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovePlayerTest {
    private final Game game = new Game();
    ArrayList<Player> players;

    @BeforeEach
    @DisplayName("Add players")
    public void initPlayers() {
        players = new ArrayList<Player>();
        players.add(new Player("pippo"));
        players.add(new Player("pluto"));
        game.setPlayers(players);
        Board.getInstance().setPlayersStartPosition(players);
    }
    @Test
    @DisplayName("Move player step")
    public void testMovePlayer(){
        Board.getInstance().move(players.get(0), 2, 3);
        assertEquals(5, Board.getInstance().getCurrentPlayerPosition(players.get(0)).getPosition());
    }
}
