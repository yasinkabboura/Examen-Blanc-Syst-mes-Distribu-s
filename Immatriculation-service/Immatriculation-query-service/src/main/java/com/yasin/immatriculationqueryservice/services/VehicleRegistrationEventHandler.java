package com.yasin.immatriculationqueryservice.services;


import com.yasin.immatriculationqueryservice.entities.Vehicle;
import com.yasin.immatriculationqueryservice.entities.VehicleOwner;
import com.yasin.immatriculationqueryservice.repositories.VehicleOwnerRepository;
import com.yasin.immatriculationqueryservice.repositories.VehicleRepository;
import coreapi.VehicleCreatedEvent;
import coreapi.VehicleOwnerUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class VehicleRegistrationEventHandler {
    private VehicleRepository vehicleRepository;
    private VehicleOwnerRepository vehicleOwnerRepository;

    public VehicleRegistrationEventHandler(VehicleRepository vehicleRepository, VehicleOwnerRepository vehicleOwnerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleOwnerRepository = vehicleOwnerRepository;
    }
    @EventHandler
    public void on(VehicleCreatedEvent event){
        log.info("************ Vehicle Query Side ==========");
        log.info("VehicleCreatedEvent occured");
        Vehicle vehicle=new Vehicle();
        vehicle.setRegistrationNumber(event.getId());
        vehicle.setModel(event.getPayload().getModel());
        vehicle.setBrand(event.getPayload().getBrand());
        vehicle.setFiscalPower(event.getPayload().getFiscalPower());

        VehicleOwner vehicleOwner=vehicleOwnerRepository.findByOwnerNationalIdCard(event.getPayload().getOwnerNationalIdCard());
        if(vehicleOwner==null){
            vehicleOwner=new VehicleOwner();
            vehicleOwner.setOwnerName(event.getPayload().getOwnerName());
            vehicleOwner.setOwnerEmail(event.getPayload().getOwnerEmail());
            vehicleOwner.setOwnerAddress(event.getPayload().getOwnerAddress());
            vehicleOwner.setOwnerPhoneNumber(event.getPayload().getOwnerPhoneNumber());
            vehicleOwner.setOwnerNationalIdCard(event.getPayload().getOwnerNationalIdCard());
            vehicleOwner.setDateBirthDay(event.getPayload().getOwnerBirthDay());
            vehicleOwner.setId(UUID.randomUUID().toString());
            vehicleOwner=vehicleOwnerRepository.save(vehicleOwner);
        }
        vehicle.setOwner(vehicleOwner);
        Vehicle saveVehicle = vehicleRepository.save(vehicle);
    }
    @EventHandler
    public void on (VehicleOwnerUpdatedEvent event, @Timestamp Instant instant) {
        log.info("Vehicle query side...");
        log.info("VehicleOwnerUpdatedEvent");
        Vehicle vehicle=vehicleRepository.findById(event.getId())
                .orElseThrow(()->new RuntimeException("Vehicle not fount =>"+event.getId()));
        VehicleOwner vehicleOwner=vehicleOwnerRepository.findByOwnerNationalIdCard(event.getPayload().getOwnerNationalIdCard());
        if(vehicleOwner==null) {
            VehicleOwner vehicleOwner1=new VehicleOwner();
            vehicleOwner1.setId(UUID.randomUUID().toString());
            vehicleOwner1.setOwnerName(event.getPayload().getOwnerName());
            vehicleOwner1.setOwnerEmail(event.getPayload().getOwnerAddress());
            vehicleOwner1.setOwnerEmail(event.getPayload().getOwnerEmail());
            vehicleOwner1.setOwnerNationalIdCard(event.getPayload().getOwnerNationalIdCard());
            vehicleOwner1.setOwnerPhoneNumber(event.getPayload().getOwnerPhoneNumber());
            vehicleOwner.setDateBirthDay(event.getPayload().getOwnerBirthDay());
            vehicleOwner = vehicleOwnerRepository.save(vehicleOwner1);
        }

        log.info("Vehicle Owner Updated ");
    }
}
