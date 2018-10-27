package cn.web.demo04;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

class MyObject extends Object {

	@Override // 垃圾回收器收回此对象时,此方法会被调用
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println("finalize..........");
	}

}

// 演示软引用与弱引用: 软引用仅仅是在堆内存不够时才会被回收, 弱引用只要垃圾回收器调用就会被回收
public class RunTimeDemo4 {
	public static void main(String[] args) throws Exception {
		// 强引用: 即使堆内存不够,此对象也不会自动回收
		MyObject object = new MyObject();
		// 当前对象采用软引用存储
		SoftReference<MyObject> ref = new SoftReference<MyObject>(object);
		// WeakReference<MyObject> ref = new WeakReference<MyObject>(object);
		// 去除强引用
		object = null;
		// 手动调用垃圾回收的方法
		System.gc();
		System.out.println(ref.get());
		List<Object> oList = new ArrayList<Object>();
		for (int i = 1; i <= 10; i++) {
			System.out.println("i:" + i);
			oList.add(new byte[1 * 1024 * 1024]);
			System.gc();
		}

		Thread.sleep(1000 * 60 * 60);

	}
}
