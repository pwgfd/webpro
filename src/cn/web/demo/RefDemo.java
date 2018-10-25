package cn.web.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 讲解反射语法
public class RefDemo {

	public static void main(String[] args) throws Exception {
		Student student = new Student();
		student.setName("admin");
		System.out.println(student.getName());
		// 采用反射实现上面的三行代码
		Class clazz = Student.class;  // 获取了Student.class文件
		Object obj = clazz.newInstance();
		// 获取当前Student.class类和它的父类 所有public方法
		Method[] methods = obj.getClass().getMethods();
		for(Method temp:methods) {
			System.out.println(temp);
		}
		
		System.out.println("===============================");
		// 获取当前Student.class类的所有方法,包括private方法
		methods = obj.getClass().getDeclaredMethods();
		for(Method temp:methods) {
			System.out.println(temp);
		}
		
		// 获取指定的方法
		Method setName = obj.getClass().getMethod("setName", String.class);
//		student.setName("admin");
		setName.invoke(obj, "abc");
		Method getName = obj.getClass().getMethod("getName");
		// student.getName()
		System.out.println(getName.invoke(obj));
		
		System.out.println("通过反射,获取属性,并且对属性进行复制操作");
		// 获取当前Student.class类和它的父类 所有public属性
		Field[] fields = obj.getClass().getFields();
		System.out.println(fields.length);
		// 获取当前Student.class类的所有属性,包括private属性
		fields = obj.getClass().getDeclaredFields();
		
		for(Field temp:fields) {
			System.out.println(temp);
		}
		
		// 获取某个一个属性,并且进行赋值操作
		Field age = obj.getClass().getDeclaredField("age");
		age.setAccessible(true);
		// student.age = 19;
		age.set(obj, 100);
		System.out.println(obj);
	}
}












