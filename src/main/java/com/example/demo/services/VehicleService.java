package com.example.demo.services;

import com.example.demo.models.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAll();
    Vehicle getById(int id);
    Vehicle getByMake(String make);
    Vehicle create(Vehicle vehicle);
    Vehicle update(int id, Vehicle vehicle);
    void delete(int id);
}
