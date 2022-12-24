package com.example.infractionqueryservice.services;
import com.example.infractionqueryservice.entities.Infraction;
import com.example.infractionqueryservice.repositories.InfractionRepository;

import coreapi.InfractionCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class InfractionEventHandler {
    private InfractionRepository infractionRepository;

    public InfractionEventHandler(InfractionRepository infractionRepository) {
        this.infractionRepository = infractionRepository;
    }
    @EventHandler
    public void on(InfractionCreatedEvent event){
        log.info("############ Contravention Query Side ################");
        log.info("ContraventionCreatedEvent occured....");
        Infraction contravention=new Infraction();
        contravention.setContraventionId(event.getId());
        contravention.setAmount(event.getPayload().getAmount());
        contravention.setInstant(event.getPayload().getTimeStamp());
        contravention.setOwnerAddress(event.getPayload().getOwnerAddress());
        contravention.setOwnerEmail(event.getPayload().getOwnerEmail());
        contravention.setOwnerNationalCardId(event.getPayload().getOwnerNationalCardId());
        contravention.setRadarLong(event.getPayload().getRadarLongitude());
        contravention.setOwnerPhoneNumber(event.getPayload().getOwnerPhoneNumber());
        contravention.setRadarId(event.getPayload().getRadarId());
        contravention.setRadarMaxSpeed(event.getPayload().getRadarMaxSpeed());
        contravention.setStatus(event.getPayload().getStatus());
        contravention.setVehicleOwner(event.getPayload().getVehicleOwner());
        contravention.setVehicleRegistrationNumber(event.getPayload().getVehicleRegistrationNumber());
        contravention.setVehicleSpeed(event.getPayload().getVehicleSpeed());
        infractionRepository.save(contravention);
    }

}
