package com.auction.Auction_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "bids")
public class bids {   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bid_id" , nullable = false, unique = true)
    private Long bidId;
    
    @Column(name="first_name")
    @Size(min = 2,max=30, message = "")
    @NotNull
    private String firstName;
    
    @Column(name="last_name")
    @Size(min = 2,max=25, message = "")
    @NotNull
    private String lastName;
    
    @Column(name="address")
    private String address;
    
    @Column(name="city")
    private String city;
    
    @Column(name="state")
    private String state;
    
    @Column(name="pin")
    private String pin;
    
    @Column(name="phone")
    @Min(value=1000000000, message="Number be of length 10")
	@Max(value=9999999999L,message="Number should be of length 11")
    @NotNull
    private Long phone;
    
    @Column(name="email_id")
    @Email(message = "Email should be valid")
    @NotNull
    private String emailId;
    
    @Column(name="product_id")
    private Long productId;
    
    @Column(name="bid_amount")
    private int bidAmount;

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		//System.out.println(prodRepository.existsById(productId));
		this.productId = productId;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}
    

}
