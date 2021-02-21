package com.vishal.vehicle.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "user_email")
    @OneToOne()
	private User user_email;
	
	@JoinColumn(name = "vendor_email")
    @OneToOne()
	private User vendor_email;
	
	@JoinColumn(name = "vehicle_name")
    @OneToOne()
	private Vehicle vehicle_name;
	
	private int ratings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser_email() {
		return user_email;
	}

	public void setUser_email(User user_email) {
		this.user_email = user_email;
	}

	public User getVendor_email() {
		return vendor_email;
	}

	public void setVendor_email(User vendor_email) {
		this.vendor_email = vendor_email;
	}

	public Vehicle getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(Vehicle vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	
	
	
	
}
