import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {

    private GameController controller;

    private JButton rollButton;
    private JLabel turnLabel;
    private JLabel diceLabel;

    private Token selectedToken = null;

    private final int size = 450;
    private final int startX = 60;
    private final int startY = 40;
    private final int cell = size / 15;

    public BoardPanel() {

        controller = new GameController();
        setLayout(null);
        setBackground(Color.WHITE);

        // Dice label
        diceLabel = new JLabel("Dice: -");
        diceLabel.setBounds(60, 520, 100, 30);
        diceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Roll button
        rollButton = new JButton("Roll 🎲");
        rollButton.setBounds(240, 520, 120, 30);

        // Turn label
        turnLabel = new JLabel("Turn: " + controller.getCurrentPlayer().getName());
        turnLabel.setBounds(380, 520, 150, 30);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 14));

        rollButton.addActionListener(e -> {

            int dice = controller.rollDice();
            diceLabel.setText("Dice: " + dice);

            controller.playTurn(selectedToken);
            selectedToken = null;

            turnLabel.setText("Turn: " + controller.getCurrentPlayer().getName());
            repaint();
        });

        add(diceLabel);
        add(rollButton);
        add(turnLabel);

        // Mouse click for selecting token
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int dice = controller.getLastDice();
                Player current = controller.getCurrentPlayer();

                for (Token t : current.getTokens()) {

                    // If token is at home and dice is not 6 → cannot select
                    if (t.getPathIndex() == -1 && dice != 6) continue;

                    int r, c;

                    if (t.getPathIndex() == -1) {
                        continue; // Home tokens are selected visually only
                    } else {
                        r = GameController.PATH[t.getPathIndex()][0];
                        c = GameController.PATH[t.getPathIndex()][1];
                    }

                    int x = startX + c * cell + 8;
                    int y = startY + r * cell + 8;
                    int d = cell - 16;

                    if (new Rectangle(x, y, d, d).contains(e.getPoint())) {
                        selectedToken = t;
                        repaint();
                        return;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // HOME AREAS
        g.setColor(Color.RED);
        g.fillRect(startX, startY, cell * 6, cell * 6);

        g.setColor(Color.GREEN);
        g.fillRect(startX + cell * 9, startY, cell * 6, cell * 6);

        g.setColor(Color.BLUE);
        g.fillRect(startX, startY + cell * 9, cell * 6, cell * 6);

        g.setColor(Color.YELLOW);
        g.fillRect(startX + cell * 9, startY + cell * 9, cell * 6, cell * 6);

        drawHomeSpots(g);

        // CENTER
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(startX + cell * 6, startY + cell * 6, cell * 3, cell * 3);

        // GRID
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 15; i++) {
            g.drawLine(startX, startY + i * cell, startX + size, startY + i * cell);
            g.drawLine(startX + i * cell, startY, startX + i * cell, startY + size);
        }

        // SAFE ZONES
        g.setColor(new Color(180, 255, 180));
        int[] safe = {0, 8, 13, 21};
        for (int s : safe) {
            int r = GameController.PATH[s][0];
            int c = GameController.PATH[s][1];
            g.fillRect(startX + c * cell, startY + r * cell, cell, cell);
        }

        // DRAW TOKENS
        for (Player p : controller.getPlayers()) {

            Point[] home = getHomePositions(p.getTokens().get(0).getColor());
            int homeIndex = 0;

            for (Token t : p.getTokens()) {

                int r, c;

                if (t.getPathIndex() == -1) {
                    r = home[homeIndex].x;
                    c = home[homeIndex].y;
                    homeIndex++;
                } else {
                    r = GameController.PATH[t.getPathIndex()][0];
                    c = GameController.PATH[t.getPathIndex()][1];
                }

                int x = startX + c * cell + 8;
                int y = startY + r * cell + 8;
                int d = cell - 16;

                g.setColor(t.getColor());
                g.fillOval(x, y, d, d);

                g.setColor(Color.BLACK);
                g.drawOval(x, y, d, d);

                if (t == selectedToken) {
                    g.drawOval(
                            startX + c * cell + 6,
                            startY + r * cell + 6,
                            cell - 12,
                            cell - 12
                    );
                }
            }
        }
    }

    // HOME SPOTS
    private void drawHomeSpots(Graphics g) {
        g.setColor(Color.WHITE);

        drawSpot(g,1,1); drawSpot(g,1,4); drawSpot(g,4,1); drawSpot(g,4,4);
        drawSpot(g,1,10); drawSpot(g,1,13); drawSpot(g,4,10); drawSpot(g,4,13);
        drawSpot(g,10,1); drawSpot(g,10,4); drawSpot(g,13,1); drawSpot(g,13,4);
        drawSpot(g,10,10); drawSpot(g,10,13); drawSpot(g,13,10); drawSpot(g,13,13);
    }

    private void drawSpot(Graphics g, int r, int c) {
        g.fillOval(
                startX + c * cell + 8,
                startY + r * cell + 8,
                cell - 16,
                cell - 16
        );
    }

    private Point[] getHomePositions(Color color) {

        if (color.equals(Color.RED)) {
            return new Point[]{ new Point(1,1), new Point(1,4), new Point(4,1), new Point(4,4) };
        }

        if (color.equals(Color.GREEN)) {
            return new Point[]{ new Point(1,10), new Point(1,13), new Point(4,10), new Point(4,13) };
        }

        if (color.equals(Color.YELLOW)) {
            return new Point[]{ new Point(10,10), new Point(10,13), new Point(13,10), new Point(13,13) };
        }

        return new Point[]{ new Point(10,1), new Point(10,4), new Point(13,1), new Point(13,4) };
    }
}
