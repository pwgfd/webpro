package cn.web.demo02;


// 每个线程都有num
public class TicketDemo extends Thread {

	private int num = 100;
	
	public TicketDemo(String name) {
		super.setName(name);
	}

	@Override
	public void run() {
		while(num>0) {
			System.out.println(Thread.currentThread().getName() + "已经出售了第" + num + "张火车票");
			num--;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		// 启动了一个新线程
		new TicketDemo("A").start();
		new TicketDemo("B").start();
		new TicketDemo("C").start();
	}
	
	
	
}
