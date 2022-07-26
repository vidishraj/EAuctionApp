package com.auction.Auction_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auction.Auction_app.model.products;
import com.auction.Auction_app.service.productsService;

@RestController
@RequestMapping("/e-auction/api/v1/seller")
public class productsController {
        @Autowired
        productsService prodService;
        
        @RequestMapping(value="/addProduct", method=RequestMethod.POST)
    	public ResponseEntity<?> createproduct(@RequestBody products prod) {
    	    return prodService.createProduct(prod);
    	}
        
        @CrossOrigin(origins = "http://localhost:3000")
        @RequestMapping(value="/show-bids/{id}", method=RequestMethod.GET)
    	public ResponseEntity<Object> listbids(@PathVariable(value = "id")Long id) {
        	/* while fetching details about the bids, we need to fetch details for the 
        	 *  Short Description, Detailed Description, Category, starting price, Bid End Date,
        	 *   along with all bids placed on it.  
        	 */
    		return prodService.listBids(id);
    	    
    	}
        
        
        @RequestMapping(value="/deleteProduct/{id}", method=RequestMethod.DELETE)
    	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id")Long id) {
    	    return prodService.deleteProduct(id);
    	}

}