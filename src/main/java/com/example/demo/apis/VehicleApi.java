package com.example.demo.apis;

import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleApi {
    private final VehicleService service;

    public VehicleApi(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("by-make/{make}")
    public ResponseEntity<Vehicle> getByMake(@PathVariable String make) {
        return ResponseEntity.ok(service.getByMake(make));
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.create(vehicle));
    }

    @PutMapping("{id}")
    public ResponseEntity<Vehicle> updatePut(@PathVariable int id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(service.update(id, vehicle));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
