package com.example.demo.apis;

import com.example.demo.models.Vehicle;
import com.example.demo.services.VehicleService;
import com.example.demo.viewmodels.VehicleCreateViewModel;
import com.example.demo.viewmodels.VehicleUpdateViewModel;
import com.example.demo.viewmodels.VehicleViewModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@Api(tags="Vehicle API")
public class VehicleApi {
    private final VehicleService service;

    public VehicleApi(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation("Get all vehicles")
    public ResponseEntity<List<VehicleViewModel>> get() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<VehicleViewModel> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("by-make/{make}")
    public ResponseEntity<VehicleViewModel> getByMake(@PathVariable String make) {
        return ResponseEntity.ok(service.getByMake(make));
    }

    @PostMapping
    public ResponseEntity<VehicleViewModel> create(@RequestBody VehicleCreateViewModel vehicle) {
        return ResponseEntity.ok(service.create(vehicle));
    }

    @PutMapping("{id}")
    public ResponseEntity<VehicleViewModel> updatePut(@PathVariable int id, @RequestBody VehicleUpdateViewModel vehicle) {
        return ResponseEntity.ok(service.update(id, vehicle));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
