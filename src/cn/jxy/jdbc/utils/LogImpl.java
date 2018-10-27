package cn.jxy.jdbc.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

// 此日志称为切面类(在运行时,动态切入)
public class LogImpl {
	// 此方法在运行时要动态切入到目标对象(被代理对象)的目标方法(连接点)
	// 后置通知
	public void showLog(JoinPoint joinPoint) {
		System.out.println("被代理对象:" + joinPoint.getTarget());
		System.out.println("连接点的参数:" + Arrays.toString(joinPoint.getArgs()));
		System.out.println("连接点方法原型" + joinPoint.getSignature());
		System.out.println("此处要打印日志.....");
		// Integer.parseInt("xxxxx");
	}

	// 捕获异常信息(在异常的时候才会切入),ex必须xml中指定
	public void showExe(JoinPoint joinPoint, Exception ex) {
		System.out.println("被代理对象:" + joinPoint.getTarget());
		System.out.println("连接点的参数:" + Arrays.toString(joinPoint.getArgs()));
		System.out.println("连接点方法原型" + joinPoint.getSignature());
		System.out.println("ex:" + ex);
	}

	public void show() {
		System.out.println("后置通知,无论是否有异常都会切入");
	}

}
