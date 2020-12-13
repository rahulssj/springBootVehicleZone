package com.project.vehicle.model;

import java.sql.Date;

public class buyDetails {
private String addressline1;
private String addressline2;
private String mobile;
private String pincode;
private int quantity;
private String state;
private String username;
private int vehicleid;
private Date date;
private float totalamount;
public float getTotalamount() {
	return totalamount;
}
private String address;
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public void setTotalamount(float totalamount) {
	this.totalamount = totalamount;
}
public buyDetails() {
	
}
public String getAddressline1() {
	return addressline1;
}
public void setAddressline1(String addressline1) {
	this.addressline1 = addressline1;
}
public String getAddressline2() {
	return addressline2;
}
public void setAddressline2(String addressline2) {
	this.addressline2 = addressline2;
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
@Override
public String toString() {
	return "buyDetails [addressline1=" + addressline1 + ", addressline2=" + addressline2 + ", mobile=" + mobile
			+ ", pincode=" + pincode + ", quantity=" + quantity + ", state=" + state + ", username=" + username
			+ ", vehicleid=" + vehicleid + ", date=" + date + ", totalamount=" + totalamount + ", address=" + address
			+ "]";
}

}
