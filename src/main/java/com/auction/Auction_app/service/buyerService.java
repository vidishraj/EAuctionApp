package com.auction.Auction_app.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.auction.Auction_app.Exceptions.ErrorResponse;
import com.auction.Auction_app.model.bids;
import com.auction.Auction_app.model.products;
import com.auction.Auction_app.repository.buyerRepository;
import com.auction.Auction_app.repository.productRepository;
import java.util.Optional;


@Service
public class buyerService {
	
	@Autowired
    buyerRepository buyRepository;
	@Autowired
	productRepository prodRepository;
	
	public ResponseEntity<?> createBids(bids bid) {
		ErrorResponse errorResponse = new ErrorResponse();
		Long id=bid.getProductId();
		if(prodRepository.existsById(id)) {
			Optional<products> prod=prodRepository.findById(id);
			
			java.sql.Date end_date=(java.sql.Date) prod.get().getBidEnd();
			long millis=System.currentTimeMillis();   
		    java.sql.Date current_date = new java.sql.Date(millis);
		    if(bid.getBidAmount()<prod.get().getStartPrice()){
	    		errorResponse.setMessage("Bid amount is less than starting price.");
		    	return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	    	}
		    if(end_date.after(current_date)) {
		    	String user_email=bid.getEmailId();
		    	if(buyRepository.findbids(user_email, bid.getProductId()).size()==0 ) {
		    		return new ResponseEntity<>(buyRepository.save(bid),HttpStatus.OK);
		    	}
		    	else{
		    		errorResponse.setMessage("User has already bid before.");
			    	return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
		    	}
		    }
		    else {
		    	errorResponse.setMessage("Bid date is over.");
		    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
		    }
		}
		else {
			errorResponse.setMessage("Product does not exist");
	    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updatebid(int amount,String Email, Long id){
		ErrorResponse errorResponse = new ErrorResponse();
		var list=buyRepository.findbids(Email, id);
		if(list.size()>0) {
			var prod_int=prodRepository.findById(id);
			var bid_end=prod_int.get().getBidEnd();
			long millis=System.currentTimeMillis();   
		    java.sql.Date current_date = new java.sql.Date(millis);
		    if(bid_end.after(current_date)) {
		    	list.get(0).setBidAmount(amount);
		    	buyRepository.save(list.get(0));
		    	errorResponse.setMessage("Successfully updated.");
		    	return new ResponseEntity<>(errorResponse,HttpStatus.OK);
		    }
		    else {
		    	errorResponse.setMessage("Bid end date has passed.");
		    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
		    }
			
		}
		errorResponse.setMessage("No bid exists on the product.");
    	return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
}