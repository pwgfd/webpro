package cn.web.demo02;


// 共用一个Num但是并发访问时有线程安全问题
public class TicketDemo2 extends Thread {

	private static int num = 100;
	
	public TicketDemo2(String name) {
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
		new TicketDemo2("A").start();
		new TicketDemo2("B").start();
		new TicketDemo2("C").start();
	}
	
	
	
}
