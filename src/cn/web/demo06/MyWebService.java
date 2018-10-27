package cn.web.demo06;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

// 发布自己的ws服务
@WebService(name = "Soap11", serviceName = "MyWebService", targetNamespace = "aa.bb.cc"

) // jdk当前是webservice服务类
public class MyWebService {
	// 只有public方法才能发布服务

	@WebMethod(operationName = "sayHi1")
	public @WebResult(name = "result") String sayHi(@WebParam(name = "name") String name) {
		return "hi " + name;
	}

	@WebMethod(exclude = true)
	public void show() {

	}

	protected void show2() {

	}

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8899/first";
		// 发布服务
		Endpoint.publish(url, new MyWebService());
		System.out.println("wsdl地址:" + url + "?WSDL");
	}
}
