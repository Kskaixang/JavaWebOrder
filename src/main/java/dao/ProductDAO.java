package dao;

import java.util.List;

import model.entity.Product;

public class ProductDAO {
	 private static List<Product> products = List.of(
			 new Product("牛肉麵",150),new Product("陽春麵",60)
			 ,new Product("番茄麵",100),new Product("麵疙瘩",90)
			 ,new Product("搶鍋面",110));  //回家作業  :多了兩個

	 public List<Product> findAll(){
		 return products;
	 }
	 
	 public Product getProduct(String item) {
		 return products.stream()
				 .filter(p -> p.getItem().equals(item))
				 .findFirst()
				 .orElseThrow();		 
	 }
	
}
