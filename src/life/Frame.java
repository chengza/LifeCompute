package life;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * A window represents a cellular automaton.
 * @author U
 *
 */
public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830525594528670636L;
	private Cell c;
	private Painter p;
	private Boolean paused = false;
	private Timer t = null;

	public Frame(int x, int y) {
		// on close action
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("LifeCompute");
		setLocationRelativeTo(null);
		Point location = getLocation();
		// add menu
		addMenu();

		c = new Cell();

		try {
			c.setData(new boolean[x][y]);
//			c.randmize();

			setSize(x * 20 + 5, y * 20 + 33 + 28);
			setResizable(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			System.exit(-1);
		}

		p = new Painter(c);
		p.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (paused) {

					paused = !paused;
					if (paused) {
						JOptionPane.showMessageDialog(Frame.this,
								"Paused. \nClose this dialog and click again to continue.");
					}
				}
			}
		});
		add(p);

		setLocation(location.x - (getSize().width / 2), location.y - (getSize().height / 2));

	}

	private void addMenu() {
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);

		JMenu options = new JMenu("Begin");
		menu.add(options);

		JMenuItem arrow = options.add("Random");

		arrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.randmize();
				p.repaint();
				if (t == null) {
					t = new Timer();
					t.setIntervalTimeMS(20);
					t.start(new Runnable() {

						@Override
						public void run() {
							synchronized (paused) {

								if (!paused) {
									c.refresh();
									p.repaint();

								}
							}
						}
					});
				}
			}
		});
	}

	public static void main(String[] args) {
		Frame f = new Frame(60, 30); 
		f.setVisible(true);
		JOptionPane.showMessageDialog(f,
				"You may click the squares to pause.\nClick Begin-Random to start a randomized condition.");
	}

}
