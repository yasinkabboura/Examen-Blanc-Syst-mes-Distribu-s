package com.example.infractioncommandservice.aggregates;


import com.example.infractioncommandservice.model.Vehicle;
import com.example.infractioncommandservice.repositories.VehicleOwnerRepository;
import com.example.infractioncommandservice.repositories.VehicleRepository;
import coreapi.*;
import lombok.extern.slf4j.Slf4j;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@Slf4j
public class InfractionAggregate {
    @AggregateIdentifier
    private String infractionId;
    private InfractionData infractionData;
    @CommandHandler
    public InfractionAggregate(NewInfractionCommand command, VehicleRepository vehicleRepository, VehicleOwnerRepository vehicleOwnerRepository) {
        log.info("===========================================");
        log.info("+++++ NewContraventionCommand received");
            InfractionData infractionData=new InfractionData();
            infractionData.setInfractionId(UUID.randomUUID().toString());
            infractionData.setOverSpeedId(command.getPayload().getOverSpeedId());
            int radarMaxSpeed = command.getPayload().getRadarMaxSpeed();
            int vehicleSpeed=command.getPayload().getVehicleSpeed();
            double amount=0;
            if((vehicleSpeed-radarMaxSpeed)>100){
                amount=4000;
            } else if((vehicleSpeed-radarMaxSpeed)>50){
                amount=3000;
            }else if((vehicleSpeed-radarMaxSpeed)>20){
                amount=1000;
            }else if((vehicleSpeed-radarMaxSpeed)>10){
                amount=500;
            }
            infractionData.setAmount(amount);
            infractionData.setVehicleSpeed(command.getPayload().getVehicleSpeed());
            infractionData.setRadarId(command.getPayload().getRadarId());
            infractionData.setTimeStamp(command.getPayload().getTimeStamp());
            infractionData.setVehicleRegistrationNumber(command.getPayload().getVehicleRegistrationNumber());
            Vehicle vehicle=vehicleRepository.findById(command.getPayload().getVehicleRegistrationNumber()).orElse(null);
            log.info("===============================================");
            log.info(command.getPayload().getVehicleRegistrationNumber());
            log.info("=>"+vehicle);
            if(vehicle!=null){
                infractionData.setVehicleOwner(vehicle.getVehicleOwner().getName());
                infractionData.setOwnerEmail(vehicle.getVehicleOwner().getEmail());
                infractionData.setOwnerAddress(vehicle.getVehicleOwner().getAddress());
                infractionData.setOwnerNationalCardId(vehicle.getVehicleOwner().getNationalCardNumber());
                infractionData.setOwnerPhoneNumber(vehicle.getVehicleOwner().getPhoneNumber());
                infractionData.setStatus(InfractionStatus.VALIDATED);
            }
            AggregateLifecycle.apply(new InfractionCreatedEvent(
                    command.getId(),
                    infractionData
            ));
    }
    @EventSourcingHandler
    public void on(InfractionCreatedEvent event){
        log.info("===========================================");
        log.info("+++++ ContraventionCreatedEvent occured");
        this.infractionId=event.getId();
        this.infractionData=event.getPayload();
        log.info("=>=>:"+this.infractionId);
    }
}
