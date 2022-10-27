import java.util.*;

public class Board {
    private static final int BOARD_LENGTH = 64;
    private static final int LAST_CELL = BOARD_LENGTH -1;
    private static ArrayList<Space> path;

    public static int getLastCell() {
        return LAST_CELL;
    }

    public Board() {
    }

    private static class BoardHolder {
        static final Board board = new Board();
    }

    public static Board getInstance() {
        return BoardHolder.board;
    }

    private static final List<Space> spaces;
    private final Map<Player, Space> playersPositions = new HashMap<>();

    static {
        ArrayList<Space> path = new ArrayList<>(65);
        for(int i = 0; i< BOARD_LENGTH; i++) {
            switch (i) {
                case 0:
                    path.add(i, new Space(i, "Start"));
                    break;
                case 6:
                    path.add(i, new Bridge(i, 12));
                    break;
                case 5:
                case 9:
                case 14:
                case 18:
                case 23:
                case 27:
                    path.add(i, new GooseSpace(i));
                    break;
                case LAST_CELL:
                    path.add(i, new LastSpace(i));
                    break;
                default:
                    path.add(i, new Space(i));
            }
        }
        path.trimToSize();
        spaces = Collections.unmodifiableList(path);
    }

    public Space[] move(Player player, int die1, int die2) {
        Space currentSpace = playersPositions.get(player);
        ArrayList<Space> moveSpaces = new ArrayList<>();
        int moveNextSpace;
        if (currentSpace != null && !(currentSpace instanceof LastSpace)) {
            int possibleNextSpace = currentSpace.getPosition() + die1 + die2;
            Space nextCell = spaces.get(Math.min(possibleNextSpace, LAST_CELL));
            moveSpaces.add(nextCell);
            moveNextSpace = nextCell.moveRolledValue(currentSpace, die1, die2);
            while (moveNextSpace != possibleNextSpace) {
                possibleNextSpace = moveNextSpace;
                nextCell = spaces.get(possibleNextSpace);
                moveSpaces.add(nextCell);
                moveNextSpace = nextCell.moveRolledValue(nextCell, die1, die2);
            }
            playersPositions.put(player, spaces.get(moveNextSpace));
        }
        return moveSpaces.toArray(new Space[0]);
    }

    public void setPlayerStartPosition(Player player) {
        playersPositions.put(player, spaces.get(0));
    }

    public void setPlayersStartPosition(List<Player> players) {
        players.forEach(this::setPlayerStartPosition);
    }
    public Space getCurrentPlayerPosition(Player player) {
        return playersPositions.get(player);
    }
}
