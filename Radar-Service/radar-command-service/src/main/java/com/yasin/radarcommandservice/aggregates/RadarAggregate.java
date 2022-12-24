package com.yasin.radarcommandservice.aggregates;

import lombok.extern.slf4j.Slf4j;
import coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@Slf4j
public class RadarAggregate {
    @AggregateIdentifier
    private String radarId;
    private double longitude;
    private double latitude;
    private int maxSpeed;


    @AggregateMember
    private List<OverSpeedMember> overSpeedMembers =new ArrayList<>();

    public RadarAggregate() {
    }
    @CommandHandler
    public RadarAggregate(CreateNewRadarCommand command) {
        log.info("CreateNewRadarCommand received");
        if (command.getPayload().getMaxSpeed() <= 0) throw new NegativeVitesseException("La vitesse du radar ne doit pas etre négative ou nulle");
        AggregateLifecycle.apply(new RadarCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(RadarCreatedEvent event){
        log.info("RadarCreatedEvent occured");
        this.radarId=event.getId();
        this.longitude=event.getPayload().getLongitude();
        this.latitude=event.getPayload().getLatitude();
        this.maxSpeed=event.getPayload().getMaxSpeed();
    }


    @CommandHandler
    public void handle(ChangeRadarSpeedLimitCommand command){
        log.info("ChangeRadarSpeedLimitCommand received");
        AggregateLifecycle.apply(new RadarSpeedLimitChangedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(RadarSpeedLimitChangedEvent event){
        log.info("RadarSpeedLimitChangedEvent to"+ event.getPayload()+" Occured");
        this.radarId=event.getId();
        this.maxSpeed=event.getPayload();
    }

    @CommandHandler
    public void handle(NewVehicleOverSpeedDetectionCommand command){
        log.info("NewVehicleOversSpeedDetectionCommand received");
        command.getPayload().setOverSpeedId(UUID.randomUUID().toString());
        command.getPayload().setRadarMaxSpeed(this.maxSpeed);
        command.getPayload().setRadarLongitude(this.longitude);
        command.getPayload().setRadarLongitude(this.latitude);
        AggregateLifecycle.apply(new VehicleOverSpeedDetectedEvent(
                command.getId(),
                command.getPayload(),
                UUID.randomUUID().toString()
        ));
    }
    @EventSourcingHandler
    public void on(VehicleOverSpeedDetectedEvent event){
        log.info("VehicleOversSpeedDetectedEvent to Occured");
        this.radarId=event.getId();
        OverSpeedMember overSpeedMember =new OverSpeedMember();
        overSpeedMember.setId(UUID.randomUUID().toString());
        overSpeedMember.setVehicleRegistrationNumber(event.getPayload().getVehicleRegistrationNumber());
        overSpeedMember.setVehicleSpeed(event.getPayload().getVehicleSpeed());
        this.overSpeedMembers.add(overSpeedMember);
        log.info("New OverSpeed added => "+overSpeedMembers.size());
    }
}
