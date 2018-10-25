package cn.web.demo;


public class Student {
	
	private Integer age;
	
	private String name;
	// 静态属于只属于类
	private static String address;
	
	
	
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		Student.address = address;
	}
	
	
	public Integer getAge() {
		System.out.println(this);
		return this.age;
	}
	public void setAge(Integer age) {
		// 永远指向当前调用的对象
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void show() {
		System.out.println(this.name + "," + this.age);
	}
	
	
}
