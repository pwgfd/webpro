package cn.web.demo04;

// 演示堆的参数设置   -XX:+TraceClassLoading -Xms10M -Xmx20M
public class RunTimeDemo {
	public static void main(String[] args) {
		// 获取当前运行时对象
		Runtime runtime = Runtime.getRuntime();
		System.out.println("可用最大堆内存:" + runtime.maxMemory() / 1024.0 / 1024 + "MB");
		System.out.println("当前可用堆空间大小:" + runtime.freeMemory() / 1024.0 / 1024 + "MB");
		System.out.println("目前提供的内存总量:" + runtime.totalMemory() / 1024.0 / 1024 + "MB");
	}
}
