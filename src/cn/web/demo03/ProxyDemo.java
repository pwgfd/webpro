package cn.web.demo03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class MyInvocationHandler implements InvocationHandler{
	
	private IProductDao target = null;
	
	public MyInvocationHandler(IProductDao target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}

	@Override // proxy：动态生成代理类，method：被调用方法   args：方法的参数
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass() + "," + method.getName() + "," + Arrays.toString(args));
		Object result = null;
		if(method.getName().equals("save") || method.getName().equals("update")) {
			System.out.println("开启事物");
			result = method.invoke(target, args);
			System.out.println("提交事物");
		}else {
			result = method.invoke(target, args);
		}
		return result;
	}
	
}

// 采用Java JDK自带的Proxy来实现动态代理(目标对象必须有接口)
public class ProxyDemo {

	public static void main(String[] args) {
		ClassLoader classLoader = ProxyDemo.class.getClassLoader();
		System.out.println(classLoader.getClass().getName());
		// 1: 类加载器，2：目标对象的的接口
		Class[] interfaces = ProductDao.class.getInterfaces();
		// 动态生成的代理类
		IProductDao proxyDao =(IProductDao)Proxy.newProxyInstance(classLoader, interfaces,new MyInvocationHandler(new ProductDao()));
		System.out.println(proxyDao.getClass());
		proxyDao.save("admin",28);
		System.out.println(proxyDao.query());
	}
}
