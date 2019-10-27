package life;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * An area the map will be on.
 * 
 * @author U
 *
 */
public class Painter extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1096749829834574644L;
	private Cell cell = null;

	public Painter(Cell cell) {
		this.cell = cell;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);

		// TODO Auto-generated method stub
		super.paintComponent(g);
		boolean[][] data = cell.getData();

		for (int a = 0; a < data.length; a++) {
			for (int b = 0; b < data[0].length; b++) {
				if (data[a][b]) {
					g.fillRect(a * 20, b * 20, 20, 20);
				} else {
					g.drawRect(a * 20, b * 20, 20, 20);
				}
			}
		}
	}
}
