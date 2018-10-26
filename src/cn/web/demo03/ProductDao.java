package cn.web.demo03;

// 静态代理.可以通过接口来创建静态代理。实现代码解耦，但是会出现很多的代理类
public class ProductDao implements IProductDao {

	public void save(String name, int age) {
		System.out.println("实现数据入库功能.....");
	}

	public static void main(String[] args) {
		// 1：先有被代理对象(目标对象)
		ProductDao dao = new ProductDao();
		// 2: 静态代理类
		ProductDaoProxy proxy = new ProductDaoProxy(dao);
		proxy.save("admin", 18);
	}

	@Override
	public String query() {
		// TODO Auto-generated method stub
		System.out.println("实现数据查詢功能.....");
		return "hehe";
	}

}

// 创建代理类,来完成非核心代码的实现
class ProductDaoProxy implements IProductDao {
	// 代理类100%必须有被代理对象
	private ProductDao target;

	public ProductDaoProxy(ProductDao target) {
		super();
		this.target = target;
	}

	public void save(String name, int age) {
		System.out.println("开启事物");
		target.save(name, age);
		System.out.println("提交事物");
	}

	@Override
	public String query() {
		return target.query();
	}

}
