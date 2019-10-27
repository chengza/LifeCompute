package life;

import java.util.Date;
import java.util.Random;

/**
 * A cells map for a cellular automaton.
 * 
 * @author U
 *
 */
public class Cell {
	private int colx = 50;
	private int rowy = 50;
	private boolean[][] data1 = new boolean[50][50];
	private boolean[][] data2 = new boolean[50][50];
	private boolean[][] data = data1;

	// set or init data
	public void setData(boolean[][] data) throws NullPointerException {
		if (data == null || data.length == 0 || data[0].length == 0) {
			throw new NullPointerException("Data exception");
		}
		this.data1 = data;
		this.data = data1;
		this.data2 = new boolean[data.length][data[0].length];
		this.colx = data.length;
		this.rowy = data[0].length;
	}

	// expose data
	public boolean[][] getData() {
		return data;
	}

	/**
	 * Get next generation data
	 */
	public void refresh() {
		boolean[][] t;
		if (data == data1) {
			t = data2;
		} else {
			t = data1;
		}

//		boolean[][] t = new boolean[colx][rowy];
		for (int x = 0; x < data.length; x++) {
			for (int y = 0; y < data[x].length; y++) {

				int c = count(x, y);

				if (data[x][y]) { // �����ǰ�ǻ��
					if (c == 2 || c == 3) { // ��Χ��2��3�����
						t[x][y] = true; // ��һ�ּ�������
					} else { // ��Χ���ŵ���������2��3
						t[x][y] = false; // ��һ������
					}
				} else { // �����ǰ������
					if (c == 3) { // ��Χ���ߵ���Ŀ��3
						t[x][y] = true; // ��һ���ǻ��
					} else { // ��Χ���ߵ���Ŀ����3
						t[x][y] = false; // ��һ������
					}
				}
			}
		}

		// set new data
		data = t;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int count(int x, int y) {
		int ret = 0;
		if (x >= 1 && y >= 1 && x < data.length - 1 && y < data[0].length - 1) {
			for (int a = x - 1; a <= x + 1; a++) {
				for (int b = y - 1; b <= y + 1; b++) {
					if (data[a][b] && a != x && b != y) {
						ret++;
					}
				}
			}

			return ret;
		} else {
			// add its self-value
			if (x == 0 && y == 0) {
				if (data[x + 1][y]) {
					ret++;
				}
				if (data[x + 1][y + 1]) {
					ret++;
				}
				if (data[x][y + 1]) {
					ret++;
				}
				return ret;

			} else if (x == 0 && y == data[0].length - 1) {
				if (data[x][y - 1]) {
					ret++;
				}
				if (data[x + 1][y - 1]) {
					ret++;
				}
				if (data[x + 1][y]) {
					ret++;
				}
				return ret;
			} else if (x == data.length - 1 && y == 0) {
				if (data[x - 1][y]) {
					ret++;
				}
				if (data[x - 1][y + 1]) {
					ret++;
				}
				if (data[x][y + 1]) {
					ret++;
				}

				return ret;
			} else if (x == data.length - 1 && y == data[0].length - 1) {
				if (data[x][y - 1]) {
					ret++;
				}
				if (data[x - 1][y]) {
					ret++;
				}
				if (data[x - 1][y - 1]) {
					ret++;
				}

				return ret;
			} else if (x == 0) {
				for (int a = x; a <= x + 1; a++) {
					for (int b = y - 1; b <= y + 1; b++) {
						if (data[a][b] && a != x && b != y) {
							ret++;
						}
					}
				}

				return ret;
			} else if (x == data.length - 1) {
				for (int a = x - 1; a <= x; a++) {
					for (int b = y - 1; b <= y + 1; b++) {
						if (data[a][b] && a != x && b != y) {
							ret++;
						}
					}
				}

				return ret;
			} else if (y == 0) {
				for (int a = x - 1; a <= x + 1; a++) {
					for (int b = y; b <= y + 1; b++) {
						if (data[a][b] && a != x && b != y) {
							ret++;
						}
					}
				}

				return ret;
			} else if (y == data[0].length - 1) {
				for (int a = x - 1; a <= x + 1; a++) {
					for (int b = y - 1; b <= y; b++) {
						if (data[a][b] && a != x && b != y) {
							ret++;
						}
					}
				}

				return ret;
			}
			return ret;
		}

	}

	/**
	 * Count living cells around (x,y)
	 * 
	 * @param x
	 * @param y
	 * @return The amount of living cells around x,y
	 */
	private int countOld(int x, int y) {
		int ret = 0;
		if (x >= 1 && y >= 1 && x < data.length - 1 && y < data[0].length - 1) {
			for (int a = x - 1; a <= x + 1; a++) {
				for (int b = y - 1; b <= y + 1; b++) {
					if (data[a][b]) {
						ret++;
					}
				}
			}
		} else {
			// add its self-value
			if (x == 0 && y == 0) {
				if (data[x + 1][y]) {
					ret++;
				}
				if (data[x + 1][y + 1]) {
					ret++;
				}
				if (data[x][y + 1]) {
					ret++;
				}

				if (data[x][y]) {
					ret++;
				}

			} else if (x == 0 && y == data[0].length - 1) {
				if (data[x][y - 1]) {
					ret++;
				}
				if (data[x + 1][y - 1]) {
					ret++;
				}
				if (data[x + 1][y]) {
					ret++;
				}
				if (data[x][y]) {
					ret++;
				}
			} else if (x == data.length - 1 && y == 0) {
				if (data[x - 1][y]) {
					ret++;
				}
				if (data[x - 1][y + 1]) {
					ret++;
				}
				if (data[x][y + 1]) {
					ret++;
				}
				if (data[x][y]) {
					ret++;
				}
			} else if (x == data.length - 1 && y == data[0].length - 1) {
				if (data[x][y - 1]) {
					ret++;
				}
				if (data[x - 1][y]) {
					ret++;
				}
				if (data[x - 1][y - 1]) {
					ret++;
				}
				if (data[x][y]) {
					ret++;
				}
			} else if (x == 0) {
				for (int a = x; a <= x + 1; a++) {
					for (int b = y - 1; b <= y + 1; b++) {
						if (data[a][b]) {
							ret++;
						}
					}
				}
			} else if (x == data.length - 1) {
				for (int a = x - 1; a <= x; a++) {
					for (int b = y - 1; b <= y + 1; b++) {
						if (data[a][b]) {
							ret++;
						}
					}
				}
			} else if (y == 0) {
				for (int a = x - 1; a <= x + 1; a++) {
					for (int b = y; b <= y + 1; b++) {
						if (data[a][b]) {
							ret++;
						}
					}
				}
			} else if (y == data[0].length - 1) {
				for (int a = x - 1; a <= x + 1; a++) {
					for (int b = y - 1; b <= y; b++) {
						if (data[a][b]) {
							ret++;
						}
					}
				}
			}
		}

		if (data[x][y]) {
			ret--;
		}

		return ret;

	}

	public void randmize() {
		Random r = new Random(new Date().getTime());
		for (int a = 0; a < data.length; a++) {
			for (int b = 0; b < data[0].length; b++) {
				data[a][b] = (r.nextInt() % 3 == 0);
			}
		}
	}

}
