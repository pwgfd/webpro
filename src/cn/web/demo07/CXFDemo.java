package cn.web.demo07;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import cn.web.demo06.MyWebService;

// cxf发布webservice服务
// ServerFactoryBean: 发布的时候不支持注解,一般我们都用子类发布：JaxWsServerFactoryBean
public class CXFDemo {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8888/cxf";
		// Endpoint.publish(url, new MyWebService());
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
		// 子类提供日志
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		factoryBean.setAddress(url);
		factoryBean.setServiceBean(new MyWebService());
		factoryBean.create();
		System.out.println(url + "?wsdl");
	}

}
