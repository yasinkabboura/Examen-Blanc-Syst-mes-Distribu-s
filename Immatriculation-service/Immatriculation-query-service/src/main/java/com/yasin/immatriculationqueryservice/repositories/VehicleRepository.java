package com.yasin.immatriculationqueryservice.repositories;

import com.yasin.immatriculationqueryservice.entities.Vehicle;
import com.yasin.immatriculationqueryservice.entities.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findVehicleByOwner(VehicleOwner vehicleOwner);
}
