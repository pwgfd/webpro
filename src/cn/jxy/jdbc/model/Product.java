package cn.jxy.jdbc.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	
	private Integer id;
	
	private String name;
	
	private String remark;
	
	private BigDecimal price;
	
	private Date date;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", remark=" + remark
				+ ", price=" + price + ", date=" + date + "]";
	}

	public Product() {
		super();
	}

	public Product(Integer id, String name, String remark, BigDecimal price) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
