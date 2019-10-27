package life.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import life.Timer;

/**
 * Test class Timer
 * 
 * @author U
 *
 */
public class TimerTest extends Timer {
	Integer test = 0;

	@Test(timeout = 6000)
	public void testSetIntervalTimeMS() {
		Timer t = new Timer();

		int a = 1000;
		t.setIntervalTimeMS(a);
		t.start(new Runnable() {
			int count = 0;

			@Override
			public void run() {
				System.out.println(new Date().toLocaleString());
				count++;
				if (count == 5) {
					Thread.currentThread().stop();
				}
			}
		});
		try {
			Thread.sleep(5500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testStart() {

		Timer t = new Timer();
		t.setIntervalTimeMS(1000);
		t.start(new Runnable() {

			@Override
			public void run() {
				test = 1;
				Thread.currentThread().stop();
			}
		});
		try {
			Thread.sleep(3000);
			assertEquals(1, (int) test);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
