import org.apache.commons.lang.StringUtils;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Pattern addPlayerPattern = Pattern.compile("add player (\\w+)", 0);
        Pattern movePattern = Pattern.compile("move (\\w+) *", 0);
        Pattern moveWithDicePattern = Pattern.compile("move (\\w+) (1|2|3|4|5|6), *(1|2|3|4|5|6)", 0);

        Game game = new Game();
        Board board = Board.getInstance();
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        String playerName = null;

        while (command != null) {
            int die1 = 0, die2 = 0, i = 0;
            Matcher addPlayerMatcher = addPlayerPattern.matcher(command);
            Matcher moveMatcher = movePattern.matcher(command);
            Matcher moveWithDiceMatcher = moveWithDicePattern.matcher(command);
            if (addPlayerMatcher.matches()) {
                playerName = addPlayerMatcher.group(1).trim();
                if (StringUtils.isNotEmpty(playerName)) {
                    Player newPlayer = new Player(playerName);
                    if (game.getPlayers().contains(newPlayer)) {
                        System.out.println(playerName + ": already existing player");
                    } else {
                        game.addPlayer(playerName);
                        System.out.println("players: " + game.getPlayers().stream().map(Object::toString).collect(Collectors.joining(", ")));
                    }
                } else {
                    throw new InvalidInputException(command, "invalid player name");
                }
            } else if (moveMatcher.matches()) {
                playerName = moveMatcher.group(++i).trim();
                Player currentPlayer = game.getPlayer(playerName);
                Random dices = new Random();
                die1 = dices.nextInt(7 - 1) + 1;
                die2 = dices.nextInt(7 - 1) + 1;
                Space currentCell = board.getCurrentPlayerPosition(currentPlayer);
                Space [] nextCell = Board.getInstance().move(currentPlayer, die1, die2);
                System.out.println(game.systemOutput(die1, die2, game.getPlayer(playerName), currentCell, nextCell));
            } else if (moveWithDiceMatcher.matches()){
                playerName = moveWithDiceMatcher.group(++i).trim();
                Player currentPlayer = game.getPlayer(playerName);
                die1 = Integer.parseInt(moveWithDiceMatcher.group(++i));
                die2 = Integer.parseInt(moveWithDiceMatcher.group(++i));
                Space currentCell = board.getCurrentPlayerPosition(currentPlayer);
                Space [] nextCell = Board.getInstance().move(currentPlayer, die1, die2);
                System.out.println(game.systemOutput(die1, die2, game.getPlayer(playerName), currentCell, nextCell));
            }else {
                throw new InvalidInputException(command, " invalid command");
            }
            command = in.nextLine();
        }
    }
}
