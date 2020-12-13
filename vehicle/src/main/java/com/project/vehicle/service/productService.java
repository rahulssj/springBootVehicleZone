package com.project.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.vehicle.dao.productDao;
import com.project.vehicle.model.User;
import com.project.vehicle.model.buyDetails;
@Service
public class productService {

	@Autowired
	productDao product;
	
public int insertSaleData(buyDetails sale) {
		
		
		return product.insertSale(sale);
	}
	
	
public buyDetails getProductSale(String name) {
	
return product.getSales(name);
}

public int getCountByUsername(String name) {
	
return product.getProductCount(name);

}
public int getCountById(int id) {
	
return product.getProductCountById(id);

}
public int getTotalSale() {
	
	
	return product.totalSale();
}

public long getTotalSaleAmount() {
	
	
	return product.totalSaledAmount();
}

public List<buyDetails> getAllSales(String name){
	
return product.getAllSales(name);
}

}



