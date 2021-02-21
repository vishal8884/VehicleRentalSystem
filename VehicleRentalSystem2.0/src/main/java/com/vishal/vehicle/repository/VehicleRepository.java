package com.vishal.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.vehicle.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
