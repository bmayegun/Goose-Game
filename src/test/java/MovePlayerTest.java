import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovePlayerTest {
    private final Game game = new Game();
    private static final Player pippo = new Player("pippo");

    @BeforeEach
    public void initPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("pippo"));
        players.add(new Player("pluto"));
        game.setPlayers(players);
        Board.getInstance().setPlayersStartPosition(players);
    }
    @Test
    @DisplayName("Move player step")
    public void testMovePlayer(){
        Board.getInstance().move(pippo, 2, 3);
        assertEquals(5, Board.getInstance().getCurrentPlayerPosition(pippo).getPosition());
    }
    @Test
    @DisplayName("Move player to bridge")
    public void testMovePlayerToBridge(){
        Board.getInstance().move(pippo, 3, 3);
        assertEquals(12, Board.getInstance().getCurrentPlayerPosition(pippo).getPosition());
    }

    @Test
    @DisplayName("Move player to last space, winner")
    public void testWinMove() {
        Board.getInstance().setPlayerPosition(pippo, 60);
        Board.getInstance().move(pippo, 1, 2);
        assertEquals(63, Board.getInstance().getCurrentPlayerPosition(pippo).getPosition());
    }
}
