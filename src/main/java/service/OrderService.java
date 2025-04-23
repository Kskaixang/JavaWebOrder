package service;

import java.util.*;

import dao.OrderDAO;
import dao.ProductDAO;
import model.dto.OrderDTO;
import model.entity.Order;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();
	private ProductDAO productDAO = new ProductDAO();
	
	
	//根據訂單項目item 新增一筆訂單 並回傳訂單顯示資訊OrderDTO 
	public OrderDTO addOrder(String item) {
		//1.新增訂單
		Order order = new Order();
		order.setItem(item);
		order.setPrice(productDAO.getProduct(item).getPrice());
		
		//傳給DAO儲存
		orderDAO.save(order);
		
		//2.回傳訂單 顯示頁面 DTO
		OrderDTO orderDTO =new OrderDTO();
		orderDTO.setMessage("您點了" + order.getItem() + "價格" + order.getPrice() + "元");
		
		return orderDTO;
	}

	
	//取得歷史資料
	public List<OrderDTO> getOrderHistory(){
		 List<Order> orders = orderDAO.findAll();
		 //將 List<Orders>轉 List<OrderDTO>
		 //同一筆資料 你應該每次都重抓更新  而不是兩個static集合在記憶體之中??
		 List<OrderDTO> orderDTOs = new ArrayList<>();
		 //一筆一筆轉
		 for(Order order : orders) {
			 OrderDTO dto = new OrderDTO();
			 dto.setMessage("您點了" + order.getItem() + "價格" + order.getPrice() + "元");
			 orderDTOs.add(dto);
		 }
		 return orderDTOs;
		 
	}
	
	
	//刪除一筆訂單 根據index
	public OrderDTO removeOrder(int index) {
		orderDAO.remove(index);
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("index=" + index + "資料刪除成功");
		return orderDTO;
	}
	
	//修改單筆資料  看不懂
	public OrderDTO updateOrder(int index, String newitem) {
		Order order = orderDAO.getOrder(index);
		order.setItem(newitem);
		orderDAO.update(index, order);
		//回報結果
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setMessage("index=" + index + "資料修改成功");
		return orderDTO;
	}
	//取得單筆 item資料
	//public OrderDTO getOrderDTO
	
}
