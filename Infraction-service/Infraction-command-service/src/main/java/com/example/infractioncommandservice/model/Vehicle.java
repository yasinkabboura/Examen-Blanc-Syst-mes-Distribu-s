package com.example.infractioncommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Vehicle {
    @Id
    private String registrationNumber;
    private String model;
    private int fiscalPower;
    @ManyToOne
    private VehicleOwner vehicleOwner;
}
