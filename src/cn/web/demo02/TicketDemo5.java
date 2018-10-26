package cn.web.demo02;


// 共用一个Num但是并发访问时有线程安全问题
// synchronized 当前只能有一个线程进入
public class TicketDemo5 extends Thread {

	private int num = 100;
	
	public TicketDemo5(String name) {
		super.setName(name);
	}

	@Override  // 会出现卖出-1的情况(没有双重判断)
	public void run() {
		while(num>0) {   // 循环卖票
			synchronized("abc") {
				if(num>0) {   //  判断是否有票
					System.out.println(Thread.currentThread().getName() + "已经出售了第" + num + "张火车票");
					num--;
				}
				
			}
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		// 启动了一个新线程
		TicketDemo5 ticket= new TicketDemo5("Ticket");
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
		new Thread(ticket).start();
	}
	
	
	
}
