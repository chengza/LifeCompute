package life.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import life.Cell;

/**
 * Test class Cell
 * 
 * @author U
 *
 */
public class CellTest {
	private Cell cell;
	private boolean[][] data = new boolean[20][20];

	@Before
	public void setUpBeforeClass() {
		cell = new Cell();
		data[1][1] = true;
		try {
			cell.setData(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetDate() {

		try {
			cell.setData(data);
			assertArrayEquals(data, cell.getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetData() {
		try {
			cell.setData(data);
			assertArrayEquals(data, cell.getData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testRefresh() {
		boolean[][] d = new boolean[20][20];
		d[0][1] = true;
		d[0][2] = true;
		d[0][3] = true;
		try {
			cell.setData(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cell.refresh();
		boolean[][] finish = new boolean[20][20];
		finish[0][2] = true;
		finish[1][2] = true;
		assertArrayEquals(finish, cell.getData());
	}

	@Ignore
	public void testRandomize() {
		// TODO
	}

	@Test(expected = NullPointerException.class)
	public void testNullSetData() {
		cell.setData(null);
	}

	@Test(expected = NullPointerException.class)
	public void testZerosSetData() {
		cell.setData(new boolean[0][0]);
	}

	@Test(expected = NullPointerException.class)
	public void test01SetData() {
		boolean[][] d = new boolean[10][0];
		cell.setData(d);
	}

	@Test(expected = NullPointerException.class)
	public void test10SetData() {
		boolean[][] d = new boolean[0][10];
		cell.setData(d);
	}

}
