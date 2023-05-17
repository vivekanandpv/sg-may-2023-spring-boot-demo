package com.example.demo.services;

import com.example.demo.models.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImplementation implements VehicleService {
    private final String key;

    public VehicleServiceImplementation(@Value("${app.key}") String key) {
        this.key = key;
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }

    @Override
    public Vehicle getById(int id) {
        Vehicle v = new Vehicle();
        v.setMake("Toyota");
        v.setModel("HyCross");

        return v;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle update(int id, Vehicle vehicle) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
