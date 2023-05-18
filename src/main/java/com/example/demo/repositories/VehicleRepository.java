package com.example.demo.repositories;

import com.example.demo.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByMakeIgnoreCase(String make);

    @Query("select v from Vehicle v where lower(v.make) = lower(?1)")
    Optional<Vehicle> findTheFirstVehicleFromAManufacturerJpql(String make);

    @Query(value = "SELECT * FROM vehicle WHERE LOWER(make) = LOWER(?1) LIMIT 1", nativeQuery = true)
    Optional<Vehicle> findTheFirstVehicleFromAManufacturerNative(String make);
}
