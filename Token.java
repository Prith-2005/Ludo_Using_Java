import java.awt.Color;

public class Token {

    private int pathIndex = -1; // -1 = home
    private Color color;

    public Token(Color color) {
        this.color = color;
    }

    public boolean isAtHome() {
        return pathIndex == -1;
    }

    public void enterBoard() {
        pathIndex = 0;
    }

    public void move(int steps, int pathLength) {
        if (pathIndex + steps >= pathLength - 1) {
            pathIndex = pathLength - 1;
            finished = true;
            return;
        }

    }

    public void sendHome() {
        pathIndex = -1;
    }

    public int getPathIndex() {
        return pathIndex;
    }

    public Color getColor() {
        return color;
    }

    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

}
