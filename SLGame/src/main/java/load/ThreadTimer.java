package load;

public class ThreadTimer extends Thread {
	public ThreadTimer() {
		
	}
	@Override
	public void run() {
		try {
			System.out.println("in thread");
			Thread.sleep(10000);
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
