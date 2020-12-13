package com.project.vehicle.model;

import java.sql.Date;

public class User {
private String username;
private String name;
private String email;
private String gender;
private String password;
private Date date;
private boolean active;
private boolean superuser;

public User() {
	}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public boolean isSuperuser() {
	return superuser;
}

public void setSuperuser(boolean superuser) {
	this.superuser = superuser;
}

@Override
public String toString() {
	return "User [username=" + username + ", name=" + name + ", email=" + email + ", gender=" + gender + ", password="
			+ password + ", date=" + date + ", active=" + active + ", superuser=" + superuser + "]";
}

}
