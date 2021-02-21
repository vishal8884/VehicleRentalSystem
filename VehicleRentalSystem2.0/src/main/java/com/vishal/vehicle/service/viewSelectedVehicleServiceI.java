package com.vishal.vehicle.service;

import java.util.Set;

import com.vishal.vehicle.entities.Vehicle;

public interface viewSelectedVehicleServiceI {
	
	
//	public int getCurrentUserId();
	
	public Set<Vehicle> selectedVehiclesSet(String[] ids);

}
