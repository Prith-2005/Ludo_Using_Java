import javax.swing.*;

public class Gameframe extends JFrame {

    public Gameframe() {
        setTitle("Ludo Game");
        setSize(650, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new BoardPanel());

        setVisible(true);
    }
}
