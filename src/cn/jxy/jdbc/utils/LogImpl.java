package cn.jxy.jdbc.utils;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;

// 此日志称为切面类(在运行时,动态切入)
public class LogImpl {
	// 此方法在运行时要动态切入到目标对象(被代理对象)的目标方法(连接点)
	// 后置通知
	public void show(JoinPoint joinPoint) {
		System.out.println("被代理对象:" + joinPoint.getTarget());
		System.out.println("连接点的参数:" + Arrays.toString(joinPoint.getArgs()));
		System.out.println("连接点方法原型" + joinPoint.getSignature());
		System.out.println("此处要打印日志.....");
	}
}
