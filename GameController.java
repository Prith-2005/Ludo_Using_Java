import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class GameController {

    // ===== PATH (USED BY BoardPanel) =====
    public static final int[][] PATH = {
            {6,0},{6,1},{6,2},{6,3},{6,4},{6,5},
            {5,6},{4,6},{3,6},{2,6},{1,6},
            {0,6},{0,7},{0,8},
            {1,8},{2,8},{3,8},{4,8},{5,8},
            {6,9},{6,10},{6,11},{6,12},{6,13},{6,14},
            {7,14},{8,14},
            {8,13},{8,12},{8,11},{8,10},{8,9},
            {9,8},{10,8},{11,8},{12,8},{13,8},{14,8},
            {14,7},{14,6},
            {13,6},{12,6},{11,6},{10,6},{9,6},
            {8,5},{8,4},{8,3},{8,2},{8,1},{8,0},
            {7,0}
    };

    // ===== SAFE ZONES =====
    private final int[] SAFE_ZONES = {0, 8, 13, 21};

    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    private int lastDice = 0;
    private final Random random = new Random();

    // ===== CONSTRUCTOR =====
    public GameController() {

        players.add(new Player("Red", Color.RED));
        players.add(new Player("Green", Color.GREEN));
        players.add(new Player("Yellow", Color.YELLOW));
        players.add(new Player("Blue", Color.BLUE));
    }

    // ===== DICE =====
    public int rollDice() {
        lastDice = random.nextInt(6) + 1;
        return lastDice;
    }

    public int getLastDice() {
        return lastDice;
    }

    // ===== MAIN TURN LOGIC =====
    public void playTurn(Token token) {

        Player current = getCurrentPlayer();

        if (token == null) {
            nextTurn();
            return;
        }

        // Token at home
        if (token.isAtHome()) {
            if (lastDice == 6) {
                token.enterBoard();
            } else {
                nextTurn();
                return;
            }
        } else {
            token.move(lastDice, PATH.length);
            checkCapture(token);
        }

        // Check winner
        if (checkWinner(current)) {
            JOptionPane.showMessageDialog(
                    null,
                    current.getName() + " WINS THE GAME 🏆🔥"
            );
            System.exit(0);
        }

        // Extra turn only on 6
        if (lastDice != 6) {
            nextTurn();
        }
    }

    // ===== CAPTURE LOGIC =====
    private void checkCapture(Token movedToken) {

        if (isSafe(movedToken.getPathIndex())) return;

        for (Player p : players) {
            if (p == getCurrentPlayer()) continue;

            for (Token t : p.getTokens()) {
                if (t.getPathIndex() == movedToken.getPathIndex()) {
                    t.sendHome(); // ☠️ KILLED
                }
            }
        }
    }

    private boolean isSafe(int index) {
        for (int s : SAFE_ZONES) {
            if (s == index) return true;
        }
        return false;
    }

    // ===== WIN CHECK =====
    private boolean checkWinner(Player p) {
        for (Token t : p.getTokens()) {
            if (!t.isFinished()) return false;
        }
        return true;
    }

    // ===== TURN HANDLING =====
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    // ===== GETTERS =====
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
