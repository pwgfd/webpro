package cn.web.demo04;

import java.util.Date;

// 演示栈内存溢出    count:35663   count:15325
public class RunTimeDemo3 {

	private int count = 0;

	public void add(double x, long y, int z) {
		double a = x;
		long b = y;
		int c = z;
		Date date = new Date();
		count++;
		add(a, b, c);
	}

	public void show() {
		try {
			add(3.14, 10l, 100);
		} catch (Throwable e) {
			System.out.println("count:" + count);
		}
	}

	public static void main(String[] args) {
		RunTimeDemo3 demo3 = new RunTimeDemo3();
		demo3.show();
	}
}
