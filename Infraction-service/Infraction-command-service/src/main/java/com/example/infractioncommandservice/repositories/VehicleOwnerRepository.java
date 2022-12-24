package com.example.infractioncommandservice.repositories;
import com.example.infractioncommandservice.model.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner,String> {
}
