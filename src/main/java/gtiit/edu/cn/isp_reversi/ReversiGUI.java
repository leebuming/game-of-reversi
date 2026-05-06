package gtiit.edu.cn.isp_reversi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReversiGUI {

    public static void main(String[] args) {
        ReversiGUI game = new ReversiGUI(8);
        game.mainLoop();
    }

    final static int marginSizePx = 80;
    final static int cellSizePx = 80;
    final static int lineWidthPx = 2;
    final static int pieceSizePx = 60;

    private Reversi reversi;
    private int size;

    private int screenWidth, screenHeight;
    private boolean[] keys = new boolean[256];
    private Graphics2D graphics;
    private BufferedImage backBuffer;
    private JLabel display;

    ReversiGUI(int size) {
        reversi = new Reversi(size);
        this.size = size;
        ReversiGUI self = this;
        screenWidth = 2 * marginSizePx + cellSizePx * size;
        screenHeight = 2 * marginSizePx + cellSizePx * size + cellSizePx;
        JFrame frame;
        backBuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        graphics = backBuffer.createGraphics();
        display = new JLabel(new ImageIcon(backBuffer));
        display.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                self.mouseClick(e.getX(), e.getY());
            }
        });
        frame = new JFrame() {{
            add(display);
            pack();
            setVisible(true);
            setFocusable(true);
            requestFocus();
            addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    self.keyPress(e.getKeyCode());
                }
                public void keyReleased(KeyEvent e) { keys[e.getKeyCode()] = false; }
            });
        }};
    }

    void keyPress(int key) {
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    void mouseClick(int x, int y) {
        int size = reversi.getSize();
        int i = (int)((x - marginSizePx) / cellSizePx);
        int j = (int)((y - marginSizePx) / cellSizePx);
        j = size - 1 - j;
        if (0 <= i && i < size && 0 <= j && j < size) {
            reversi.playAt(i, j);
        }
    }

    void mainLoop() {
        while(true) {
            long start = System.currentTimeMillis();
            redraw();
            display.getGraphics().drawImage(backBuffer, 0, 0, null);
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed < 30) {
                try {
                    Thread.sleep(30 - elapsed);
                } catch (Exception e) {
                }
            }
        }
    }

    void redraw() {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, screenWidth, screenHeight);

        graphics.setColor(Color.BLACK);

        // Draw vertical lines
        for (int i = 0; i <= size; i++) {
            graphics.fillRect(
                marginSizePx + cellSizePx * i,
                marginSizePx,
                lineWidthPx,
                cellSizePx * size + lineWidthPx
            );
        }

        // Draw horizontal lines
        for (int i = 0; i <= size; i++) {
            graphics.fillRect(
                marginSizePx,
                marginSizePx + cellSizePx * i,
                cellSizePx * size + lineWidthPx,
                lineWidthPx
            );
        }

        // Draw pieces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                drawCell(i, j);
            }
        }

        // Draw current turn
        int cx = marginSizePx + pieceSizePx / 2;
        int cy = marginSizePx + cellSizePx * size + cellSizePx / 2;
        if (!reversi.gameEnded()) {
            this.drawPiece(reversi.currentTurn(), cx, cy);
        }

        // Draw points
        cx = marginSizePx + cellSizePx * size - 2 * cellSizePx;
        cy = marginSizePx + cellSizePx * size + 2 * cellSizePx / 3;
        graphics.setFont(new Font("Serif", Font.BOLD, 36));
        if (reversi.gameEnded()) {
            graphics.setColor(Color.RED);
        }
        graphics.drawString(
            reversi.numberOfPieces(PlayerColor.Black) + "●"
            + " – "
            + reversi.numberOfPieces(PlayerColor.White) + "○", cx, cy
        );
    }

    private void drawCell(int i, int j) {
        if (!reversi.hasPieceAt(i, j)) {
            return;
        }
        int jj = size - 1 - j;
        int cx = marginSizePx + cellSizePx * i + cellSizePx / 2;
        int cy = marginSizePx + cellSizePx * jj + cellSizePx / 2;
        this.drawPiece(reversi.pieceAt(i, j), cx, cy);
    }

    private void drawPiece(PlayerColor color, int cx, int cy) {
        int x = cx - pieceSizePx / 2;
        int y = cy - pieceSizePx / 2;
        int w = pieceSizePx;
        int h = pieceSizePx;
        if (color == PlayerColor.Black) {
            graphics.setColor(Color.BLACK);
            graphics.fillOval(x, y, w, h);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.setStroke(new BasicStroke(2));
            graphics.drawOval(x, y, w, h);
        }
    }
}

