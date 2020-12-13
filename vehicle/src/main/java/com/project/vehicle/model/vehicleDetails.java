package com.project.vehicle.model;

public class vehicleDetails {

	private int id;
	private String brand;
	private String model;
	private float mileage;
	private float engine;
	private float price;
	private String transmission_type;
	private String image;
	private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	private String disabled;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public vehicleDetails(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public float getMileage() {
		return mileage;
	}
	public void setMileage(float mileage) {
		this.mileage = mileage;
	}
	public float getEngine() {
		return engine;
	}
	public void setEngine(float engine) {
		this.engine = engine;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTransmission_type() {
		return transmission_type;
	}
	public void setTransmission_type(String transmission_type) {
		this.transmission_type = transmission_type;
	}
	@Override
	public String toString() {
		return "vehicleDetails [id=" + id + ", brand=" + brand + ", model=" + model + ", mileage=" + mileage
				+ ", engine=" + engine + ", price=" + price + ", transmission_type=" + transmission_type + ", image="
				+ image + ", quantity=" + quantity + ", disabled=" + disabled + "]";
	}
	

}
