package com.example.infractioncommandservice.repositories;


import com.example.infractioncommandservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
}
