package com.auction.Auction_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auction.Auction_app.model.bids;
@Repository
public interface buyerRepository extends JpaRepository<bids, Long> {
	@Query(value="select * from bids where product_id = ?1",nativeQuery = true)
    List<bids> findbidsonproduct(Long id);
    
    @Query(value="select * from bids where email_id = ?1 and product_id=?2",nativeQuery = true)
    List<bids> findbids(String email, Long id);
    
}