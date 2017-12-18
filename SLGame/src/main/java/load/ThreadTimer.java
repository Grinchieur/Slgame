package load;

public class ThreadTimer extends Thread {
	int time = 10000;
	public ThreadTimer() {
		
	}
public ThreadTimer(int howlong) {
		time = howlong;
	}
	@Override
	public void run() {
		try {
			System.out.println("in thread");
			Thread.sleep(time);
			
			System.out.println("thread fini");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void timerGo() throws InterruptedException {
		Thread.sleep(10000);
		
	}
}
