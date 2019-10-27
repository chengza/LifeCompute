package life;

/**
 * A timer which runs a given task after a certain period in loop. 
 * @author U
 *
 */
public class Timer {
	private int ms = 0;

	public void setIntervalTimeMS(int ms) {
		this.ms = ms;
	}

	public void start(Runnable r) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						r.run();
						Thread.sleep(ms);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
