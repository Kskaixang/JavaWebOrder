package controller;

import java.io.IOException;
import java.util.*;

import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.OrderDTO;
import model.entity.Product;
import service.OrderService;
import service.ProductService;

@WebServlet("/order")
public class OrderServlet extends HttpServlet{	
	private OrderService orderService = new OrderService();
	private ProductService productService = new ProductService();
	
	//查看歷史資料 你可以從index  看到 <form class="pure-form" mathod="get"  這個get在歷史請求上
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到歷史紀錄
		List<OrderDTO> orderDTOs = orderService.getOrderHistory();
		
		//計算總金額
		int totalPrice = orderDTOs.stream()
				.mapToInt(dto -> productService.getPrice(dto.getMessage()))
				.sum();
		
		//導到JSP 並帶上歷史紀錄資料
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/history.jsp");
		req.setAttribute("orderDTOs", orderDTOs);
		req.setAttribute("totalPrice", totalPrice);
		rd.forward(req, resp);
	}
	//接收訂單 你可以從index  看到 <form class="pure-form" mathod="post"   這個post在送訂單上
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String item = req.getParameter("item"); //取得使用者訂購的商品
		//新增訂單
		OrderDTO orderDTO = orderService.addOrder(item);
		//導到JSP 並帶 反饋資料 (OrderDTO)
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
		req.setAttribute("orderDTO", orderDTO);
		rd.forward(req, resp);
		
		
	}
	
	

}
