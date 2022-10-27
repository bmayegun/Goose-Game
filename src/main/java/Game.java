import java.util.*;

public class Game {
    public Set<Player> players = new LinkedHashSet<Player>();
    private final Map<String , Integer> positions = new HashMap<String, Integer>();

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players.clear();
        this.players.addAll(players);
    }

    public Player getPlayer(String playerName) {
        return players.stream().filter(p->p.getName().equals(playerName)).findFirst().orElse(null);
    }

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    public String addPlayer(String playerName) {
        Player newPlayer = new Player(playerName);
        boolean added = addPlayer(newPlayer);
        if (added) {
            Board.getInstance().setPlayerStartPosition(newPlayer);
            return players.toString();
        } else {
            return newPlayer.getName() + ": already existing player";
        }
    }

    public String systemOutput(int die1, int die2, Player player, Space startingCell, Space[] destinationCells) {
        StringBuilder output = new StringBuilder();
        int cellsLength = destinationCells.length;
        if (cellsLength == 0 ) {
            output.append(player).append(" rolls ").append(die1).append(", ").append(die2).append(". ").append(player).append(" cannot move from ")
                    .append(startingCell).append("as the dice value are too high. ").append(player).append(" remains on ").append(startingCell);
        } else {
            Space destinationCell = destinationCells[cellsLength-1];
            if (cellsLength > 1) {
                Optional<Space> winnerCell = Arrays.stream(destinationCells).filter(cell->(cell instanceof LastSpace)).findAny();
                Optional<Space> bridgeCell = Arrays.stream(destinationCells).filter(cell->(cell instanceof Bridge)).findAny();
                Optional<Space> gooseCell = Arrays.stream(destinationCells).filter(cell->(cell instanceof GooseSpace)).findAny();
                if (winnerCell.isPresent()) {
                    output.append(player)
                            .append(" rolls ")
                            .append(die1).append(", ")
                            .append(die2).append(". ")
                            .append(player).append(" moves from ")
                            .append(startingCell).append(" to ")
                            .append(winnerCell.get()).append(". ")
                            .append(player).append(" bounces! ")
                            .append(player).append(" returns to ")
                            .append(destinationCell);
                } else if (bridgeCell.isPresent()){
                    int i = 0;
                    output.append(player)
                            .append(" rolls ")
                            .append(die1)
                            .append(", ")
                            .append(die2)
                            .append(". ")
                            .append(player)
                            .append(" moves from ")
                            .append(startingCell)
                            .append(" to ")
                            .append(destinationCells[i++])
                            .append(". ")
                            .append(player)
                            .append(" jumps to ")
                            .append(destinationCells[i++]);
                } else if (gooseCell.isPresent()) {
                    int i = 0;
                    output.append(player).append(" rolls ").append(die1).append(", ").append(die2).append(". ").append(player).append(" moves from ")
                            .append(startingCell).append(" to ").append(destinationCells[i++]);
                    while (i<cellsLength) {
                        output.append(". ").append(player).append(" moves again and goes to ").append(destinationCells[i++]);
                    }
                }
            } else {
                output.append(player)
                        .append(" rolls ")
                        .append(die1)
                        .append(", ")
                        .append(die2)
                        .append(". ")
                        .append(player)
                        .append(" moves from ")
                        .append(startingCell)
                        .append(" to ")
                        .append(destinationCell)
                        .append((destinationCell instanceof LastSpace) ? ". " + player + " Wins!!" : "");
            }
        }
        return output.toString();
    }
}
