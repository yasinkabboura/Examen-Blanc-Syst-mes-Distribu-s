package com.yasin.immatriculationqueryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Vehicle {
    @Id
    private String registrationNumber;
    private String brand;
    private String model;
    private  int  fiscalPower;
    @ManyToOne( fetch = FetchType.EAGER)
    private VehicleOwner owner;
}
