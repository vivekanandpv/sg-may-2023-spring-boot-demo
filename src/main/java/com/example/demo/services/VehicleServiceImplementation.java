package com.example.demo.services;

import com.example.demo.models.Vehicle;
import com.example.demo.repositories.VehicleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImplementation implements VehicleService {
    private final VehicleRepository repository;

    public VehicleServiceImplementation(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @Override
    public Vehicle getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));
    }

    @Override
    public Vehicle getByMake(String make) {
        //  jpql
//        return repository.findTheFirstVehicleFromAManufacturerJpql(make)
//                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        //  native
        return repository.findTheFirstVehicleFromAManufacturerNative(make)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        repository.saveAndFlush(vehicle);
        return vehicle;
    }



    @Override
    public Vehicle update(int id, Vehicle vehicle) {
        Vehicle vehicleDb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        BeanUtils.copyProperties(vehicle, vehicleDb);
        repository.saveAndFlush(vehicleDb);

        return vehicleDb;
    }

    @Override
    public void delete(int id) {
        Vehicle vehicleDb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        repository.delete(vehicleDb);
    }
}
