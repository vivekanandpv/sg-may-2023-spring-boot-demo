package com.example.demo.services;

import com.example.demo.models.Vehicle;
import com.example.demo.viewmodels.VehicleCreateViewModel;
import com.example.demo.viewmodels.VehicleUpdateViewModel;
import com.example.demo.viewmodels.VehicleViewModel;

import java.util.List;

public interface VehicleService {
    List<VehicleViewModel> getAll();
    VehicleViewModel getById(int id);
    VehicleViewModel getByMake(String make);
    VehicleViewModel create(VehicleCreateViewModel vehicle);
    VehicleViewModel update(int id, VehicleUpdateViewModel vehicle);
    void delete(int id);
}
