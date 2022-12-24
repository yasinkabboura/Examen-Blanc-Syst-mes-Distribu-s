package com.yasin.immatriculationqueryservice.repositories;


import com.yasin.immatriculationqueryservice.entities.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, String> {
    VehicleOwner findByOwnerNationalIdCard(String nationalIdCard );
}
