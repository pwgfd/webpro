package cn.web.demo02;

// 共用一个Num但是并发访问时有线程安全问题
// synchronized 当前只能有一个线程进入
public class TicketDemo6 extends Thread {

	private int num = 100;

	public TicketDemo6(String name) {
		super.setName(name);
	}

	@Override // 会出现卖出-1的情况(没有双重判断)
	public void run() {
		while (num > 0) { // 循环卖票
			// "abc" 在常量池中, new String("abc"):在堆中
			// () 代表
			synchronized (this) {
				if (num > 0) { // 判断是否有票
					System.out.println(Thread.currentThread().getName() + "已经出售了第" + num + "张火车票");
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					num--;
				}

			}

		}
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		// 启动了一个新线程
		TicketDemo6 ticket = new TicketDemo6("Ticket");
		new Thread(ticket).start();
		new Thread(ticket).start();
	}

}
