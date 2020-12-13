package com.project.vehicle.model;

import java.sql.Date;

public class useDashboard {
	private String address;
	private String mobile;
	private String pincode;
	private int quantity;
	private String state;
	private String username;
	private int vehicleid;
	private Date date;
	private float totalamount;
	private String brand;
	private String image;
	public useDashboard() {
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "useDashboard [address=" + address + ", mobile=" + mobile + ", pincode=" + pincode + ", quantity="
				+ quantity + ", state=" + state + ", username=" + username + ", vehicleid=" + vehicleid + ", date="
				+ date + ", totalamount=" + totalamount + ", brand=" + brand + ", image=" + image + "]";
	}
	
}
