package com.auction.Auction_app.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.auction.Auction_app.model.products;
import com.auction.Auction_app.repository.buyerRepository;
import com.auction.Auction_app.repository.productRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.auction.Auction_app.Exceptions.ErrorResponse;
@Service
public class productsService {
	
	@Autowired
    productRepository prodRepository;
	@Autowired
	buyerRepository buyRepository;
	
	public ResponseEntity<?> createProduct(products prod) {
		
		var category=prod.getCategory();
		
		java.sql.Date bid_end=(java.sql.Date) prod.getBidEnd();
		long millis=System.currentTimeMillis();   
	    java.sql.Date current_date = new java.sql.Date(millis);
		

		ErrorResponse errorResponse = new ErrorResponse();
		
		if(category.equals("Painting") || category.equals("Ornament")  || category.equals("Sculptor")) {
			if(bid_end.after(current_date)) {
				return new ResponseEntity<>(prodRepository.save(prod), HttpStatus.OK);
			}
			else {
				errorResponse.setMessage("Bid end date is wrong.");
			}
		}
		else {
			errorResponse.setMessage("Wrong Category is entered");
		}
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	public ResponseEntity<Object> listBids(Long product_id){
		Optional<products> prod1=prodRepository.findById(product_id);
		Map<String, Object> map=new HashMap<String, Object>();
		if( prod1.isPresent()) {
			var prod2=prod1.get();
			map.put("ProductName",prod2.getProductName());
			map.put("Description", prod2.getDescription());
			map.put("LongDescription", prod2.getLongDescription());
			map.put("Category", prod2.getCategory());
			map.put("StartingPrice", prod2.getStartPrice());
			map.put("BidEnd", prod2.getBidEnd());
			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			//bid amount, email, name and mobile.
			var list1=buyRepository.findbidsonproduct(product_id);
			if(list1.size()>0) {
				for(int i=0;i<list1.size();i++) {		
					Map<String, Object> bids_map=new HashMap<String, Object>();
					bids_map.put("BidAmount",list1.get(i).getBidAmount());
					bids_map.put("FirstName",list1.get(i).getFirstName());
					bids_map.put("Email",list1.get(i).getEmailId());
					bids_map.put("Phone",list1.get(i).getPhone());
					list.add(i, bids_map);
				}
				
			}
			map.put("Bids", list);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("Product does not exist.");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> deleteProduct(Long id){
		ErrorResponse errorResponse = new ErrorResponse();
		Optional<products> prod1=prodRepository.findById(id);
		
		if( prod1.isPresent()) {
			var prod2=prod1.get();
			Long prod_id=prod2.getProduct_id();
			java.sql.Date end_date=(java.sql.Date) prod2.getBidEnd();
			long millis=System.currentTimeMillis();   
			java.sql.Date current_date = new java.sql.Date(millis);  
			
			if(end_date.after(current_date)) {
				if(buyRepository.findbidsonproduct(prod_id).size()==0) {
					prodRepository.deleteById(id);
					errorResponse.setMessage("Product successfully deleted.");
					 return new ResponseEntity<>(errorResponse, HttpStatus.OK);
				}
				else {
					errorResponse.setMessage("There are bids on this product currently");
					return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
				}
			}
			else {
				errorResponse.setMessage("End date is past the bid end date.");
				return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			}
		}
		errorResponse.setMessage("Product does not exist.");
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
