package cn.web.demo04;

import java.util.ArrayList;
import java.util.List;

// 演示堆内存溢出    演示堆的参数设置  -Xms15M  -Xmn5M -Xmx30M
public class RunTimeDemo2 {
	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		List<Object> oList = new ArrayList<Object>();
		for (int i = 1; i <= 30; i++) {
			System.out.println("i:" + i);
			oList.add(new byte[1 * 1024 * 1024]);
			System.out.println("可用最大堆内存:" + runtime.maxMemory() / 1024.0 / 1024 + "MB");
			System.out.println("当前可用堆空间大小:" + runtime.freeMemory() / 1024.0 / 1024 + "MB");
			System.out.println("目前提供的内存总量:" + runtime.totalMemory() / 1024.0 / 1024 + "MB");
			Thread.sleep(500);
		}

		Thread.sleep(1000 * 60 * 60);

	}
}
