package com.example.demo.services;

import com.example.demo.models.Vehicle;
import com.example.demo.repositories.VehicleRepository;
import com.example.demo.viewmodels.VehicleCreateViewModel;
import com.example.demo.viewmodels.VehicleUpdateViewModel;
import com.example.demo.viewmodels.VehicleViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImplementation implements VehicleService {
    private final VehicleRepository repository;

    public VehicleServiceImplementation(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VehicleViewModel> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleViewModel getById(int id) {
        return toViewModel(
                repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"))
        );
    }

    @Override
    public VehicleViewModel getByMake(String make) {
        //  jpql
//        return repository.findTheFirstVehicleFromAManufacturerJpql(make)
//                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        //  native
        return toViewModel(
                repository.findTheFirstVehicleFromAManufacturerNative(make)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"))
        );
    }

    @Override
    public VehicleViewModel create(VehicleCreateViewModel viewModel) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(viewModel, vehicle);
        repository.saveAndFlush(vehicle);
        return toViewModel(vehicle);
    }



    @Override
    public VehicleViewModel update(int id, VehicleUpdateViewModel viewModel) {
        Vehicle vehicleDb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        BeanUtils.copyProperties(viewModel, vehicleDb);
        repository.saveAndFlush(vehicleDb);

        return toViewModel(vehicleDb);
    }

    @Override
    public void delete(int id) {
        Vehicle vehicleDb = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the vehicle"));

        repository.delete(vehicleDb);
    }

    private VehicleViewModel toViewModel(Vehicle entity) {
        VehicleViewModel viewModel = new VehicleViewModel();
        BeanUtils.copyProperties(entity, viewModel);
        return viewModel;
    }
}
