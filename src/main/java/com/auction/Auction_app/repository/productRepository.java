package com.auction.Auction_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.Auction_app.model.products;
@Repository
public interface productRepository extends JpaRepository<products, Long> {

}