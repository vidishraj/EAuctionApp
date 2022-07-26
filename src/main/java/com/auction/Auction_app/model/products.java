package com.auction.Auction_app.model;

import java.sql.Date;

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
@Table(name = "products")
public class products {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
    private Long product_id;
    
    @Column(name="product_name")
    @NotNull
    @Size(min = 2,max=30, message = "")
    private String productName;
    
    @Column(name="LongDescription")
    private String LongDescription;
    
    @Column(name="description")
    private String description;
    
    @Column(name="category")
    private String category;
    
    @Column(name="starting_price")
    private Long startPrice;
    
    @Column(name="bid_end")
    private Date bidEnd;
    
    //The seller data is mentioned here
    @Column(name="firstname")
    @NotNull
    @Size(min = 2, max=30, message = "")
    private String firstName;
	
	@Column(name="lastname")
	@NotNull
	@Size(min = 2, max= 25, message = "")
    private String lastName;
	
	@Column(name="address")
    private String address;
	
	@Column(name="city")
    private String city;
	
	@Column(name="state")
    private String state;
	
	@Column(name="pin")
    private int pin;
	
	@Column(name="phone")
	@NotNull
	@Min(value=1000000000, message="Number be of length 10")
	@Max(value=9999999999L,message="Number should be of length 10")
	//@Size(min = 10, max=10, message = "")
	private Long phone;
	
	@Column(name="email")
	@NotNull
	@Email(message = "Email should be valid")
    private String email;

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;			
	}

	public Long getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice=startPrice;
	}

	public Date getBidEnd() {
		return bidEnd;
	}

	public void setBidEnd(Date bidEnd) {
		this.bidEnd = bidEnd;
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

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
    public String toString() {
        return "Product [id=" +product_id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", emailId=" + email
    + "]";
    }

	public String getLongDescription() {
		return LongDescription;
	}

	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}
}
