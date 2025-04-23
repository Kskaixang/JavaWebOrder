package dao;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import model.entity.Order;

public class OrderDAO {
	//利用List 來模擬資料庫/資料表 (稱為InMemoryDatabase)
	private static List<Order> orders = new CopyOnWriteArrayList<>();
	//存入一筆資料
	public void save(Order order) {
		orders.add(order);
	}
	//取得所有資料
	public List<Order> findAll(){
		return orders;
	}
	//刪除一筆資料
	public void remove(int index) {
		orders.remove(index);
	}	
	//修改  修改通常會配合"取得單筆"
	public void update(int index,Order newOrder) {
		orders.set(index, newOrder);
	}	
	//取得單筆資料
	public Order getOrder(int index) {
		return orders.get(index);
	}

}
