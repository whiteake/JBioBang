import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	
	boolean[][] tiles = new boolean[10][10];
	boolean[][] chosen = new boolean[10][10];
	int score = 0;

	int hoverx, hovery;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tiles[i][j] = new Random().nextBoolean();
			}
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("Biobang");
		setResizable(false);
		setLocationRelativeTo(null);
		addMouseListener(new Mouse());
		setVisible(true);

	}

	public void paint(Graphics g) {
		g.translate(0, 32);
		g.setColor(Color.white);
		g.fillRect(0, 0, 400, 400);

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {

				g.setColor(Color.lightGray);
				g.fillRect(i * 32, j * 32, 32, 32);
				g.setColor(Color.black);

				g.drawRect(i * 32, j * 32, 32, 32);
			}
		}

		for (int k = 0; k < chosen.length; k++) {
			for (int l = 0; l < chosen[0].length; l++) {
				if (chosen[k][l]) {
					g.setColor(Color.darkGray);
					g.fillRect(k * 32, l * 32, 32, 32);
				}
			}
		}

		g.setColor(Color.black);
		g.drawString("Score: " + score, 320, 32);
	}

	class Mouse extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			int x = (e.getX() / 32);
			int y = (e.getY() / 32 - 1);
			if (tiles[x][y]) {
				JOptionPane.showMessageDialog(null, "You hit a biobomb!", "BioBomb", JOptionPane.PLAIN_MESSAGE);
				score = 0;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						tiles[i][j] = new Random().nextBoolean();
					}
				}
				for (int k = 0; k < 9; k++) {
					for (int l = 0; l < 9; l++) {

						chosen[k][l] = false;
					}
				}
				repaint();
			} else {
				if (!chosen[x][y]) {
					score++;
				}
				chosen[x][y] = true;
				repaint();
			}
		}
	}
}
