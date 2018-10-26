package cn.web.demo02;

// 演示线程互斥操作
public class ThreadDemo7 {

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String name = Thread.currentThread().getName();
					synchronized ("刀") {
						System.out.println(name + "已经获取刀资源");
						Thread.sleep(200);
						synchronized ("叉") {
							System.out.println(name + "已经获取叉资源,正在就餐!");
							Thread.sleep(1000);
						}
						System.out.println(name + "已经释放叉资源");

					}
					System.out.println(name + "已经释放刀资源");

				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

			}
		}).start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String name = Thread.currentThread().getName();
					synchronized ("叉") {
						System.out.println(name + "已经获取叉资源");
						Thread.sleep(200);
						synchronized ("刀") {
							System.out.println(name + "已经获取刀资源,正在就餐!");
							Thread.sleep(1000);
						}
						System.out.println(name + "已经释放刀资源");

					}
					System.out.println(name + "已经释放叉资源");

				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}

			}
		}).start();

	}
}
