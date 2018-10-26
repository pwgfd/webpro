package cn.web.demo02;


// 共用一个Num但是并发访问时有线程安全问题
// synchronized 当前只能有一个线程进入
public class TicketDemo3 extends Thread {

	private int num = 100;
	
	public TicketDemo3(String name) {
		super.setName(name);
	}

	@Override  // 一个线程会出售所有车票
	public synchronized void run() {
		while(num>0) {
			System.out.println(Thread.currentThread().getName() + "已经出售了第" + num + "张火车票");
			num--;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		// 启动了一个新线程
		TicketDemo3 ticket= new TicketDemo3("Ticket");
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}
	
	
	
}
