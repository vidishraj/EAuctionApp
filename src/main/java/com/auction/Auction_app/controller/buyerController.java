package com.auction.Auction_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auction.Auction_app.model.bids;
import com.auction.Auction_app.service.buyerService;

@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class buyerController {
        @Autowired
        buyerService buyService;
        
        @RequestMapping(value="/place-bid ", method=RequestMethod.POST)
    	public ResponseEntity<?> createbid(@RequestBody bids bid) {
    	    return buyService.createBids(bid);
    	}
        
        @RequestMapping(path="/update-bid/{productId}/{emailid}/{newBidAmount}")
    	public ResponseEntity<?> createbid(@PathVariable Long productId,@PathVariable String emailid,@PathVariable( "newBidAmount") int newBidAmount ){
        	System.out.println(productId);
        	System.out.println(emailid);
        	System.out.println(newBidAmount);
    	    return buyService.updatebid(newBidAmount, emailid, productId);
    	}

}