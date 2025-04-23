package model.entity;


public class Order {
	private String item;
	private Integer price;
	
	public Order() {		
	}
	
	public Order(String item, Integer price) {
		super();
		this.item = item;
		this.price = price;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	

}
