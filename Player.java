import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Token> tokens;

    public Player(String name, Color color) {
        this.name = name;
        tokens = new ArrayList<>();

        // 4 tokens per player
        for (int i = 0; i < 4; i++) {
            tokens.add(new Token(color));
        }
    }

    public String getName() {
        return name;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
