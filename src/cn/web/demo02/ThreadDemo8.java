package cn.web.demo02;

//演示线程通信操作
public class ThreadDemo8 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized ("A") {
					String name = Thread.currentThread().getName();
					System.out.println("1:当前线程名称:" + name);
					try {
						// 当前线程在A临界区处于等待状态(释放锁), 在等待时其它线程可以进入临界区
						"A".wait();
						System.out.println("4:" + name + "已经被唤醒!");
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}

			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized ("A") {
					String name = Thread.currentThread().getName();
					System.out.println("2:当前线程名称:" + name);
					// 唤醒在此对象监视器上等待的单个线程
					"A".notify();
					System.out.println("3:当前线程名称:" + name);
				}

			}
		}).start();
	}
}
