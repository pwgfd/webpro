package cn.web.demo;

import java.io.File;
import java.util.Date;

public class JVMDemo {
	// 类要先加载，用来加载类的，称为类加载器 ClassLoader
	public static void main(String[] args) throws Exception {
		// Student.class (此文件是一个对象，Java中是Class类型)
		Class clazz1 = Class.forName("cn.web.demo01.Student");
		Class class2 = Student.class;
		Class class3 = new Student().getClass();
		System.out.println(clazz1==class2 && class2==class3);
		// 反射可以获取Student.class文件的中任何信息
		// 获取加载当前类(Student.class)的类加载器
		ClassLoader classLoader = clazz1.getClassLoader();		
//		sun.misc.Launcher$ExtClassLoader
//		sun.misc.Launcher$AppClassLoader
		while(classLoader!=null) {
			System.out.println(classLoader.getClass().getName());
			// 返回当前类加载器父类
			classLoader = classLoader.getParent();
		}
		
		
		
//		// JVM: 堆、栈、方法区(代码区)
//		// new Student(); 堆中, 指向对象的变量存储栈中
//		Student student = new Student();
//		student.setAge(18);
//		Student.setAddress("北京");
//		System.out.println(student.getAge() + "," + Student.getAddress());
//		Student student2 = new Student();
//		student2.setAge(19);
//		Student.setAddress("深圳");
//		System.out.println(student2.getAge() + "," + Student.getAddress());
//		System.out.println(student.getAge() + "," + Student.getAddress());
		
	}

}
