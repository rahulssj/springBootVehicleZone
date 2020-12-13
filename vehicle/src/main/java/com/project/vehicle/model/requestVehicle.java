package com.project.vehicle.model;

import java.sql.Date;

public class requestVehicle {

private int id;
private String username;
private int vehicleid;
private Date date;
private String status;
public requestVehicle() {
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "requestVehicle [id=" + id + ", username=" + username + ", vehicleid=" + vehicleid + ", date=" + date
			+ ", status=" + status + "]";
}

}
