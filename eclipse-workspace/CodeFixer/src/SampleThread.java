
public class SampleThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Main ThreadBefore");
new MyThread();
try {
	Thread.sleep(100);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
System.out.println("Main Thread After");
	}

}
class MyThread implements  Runnable {
	public void run() {
		System.out.println("Hello");
		try {
			Thread.sleep(150);
			System.out.println("after sleep");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
Thread t;
	public MyThread() {
		t=new Thread(this,"MyThread");
		t.start();
		// TODO Auto-generated constructor stub
	}
	
}