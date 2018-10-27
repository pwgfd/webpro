package cn.web.demo05;

// wsimport -encoding utf-8 -s . -p cn.web.demo05 url
public class _WSTest {

	public static void main(String[] args) {
		// 调用第三方提供的webservice服务

		// 1: 获取wsdl文件提供的服务名 wsdl:service
		MobileCodeWS ws = new MobileCodeWS();
		// 2: 获取ws提供的服务类型: soap1.1 soap1.2 get post
		MobileCodeWSSoap soap = ws.getMobileCodeWSSoap();
		// 3: 调用服务提供方法
		String result = soap.getMobileCodeInfo("18312345678", null);
		System.out.println(result);
	}
}
