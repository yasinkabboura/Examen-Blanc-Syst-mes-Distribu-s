package com.yasin.immatriculationqueryservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class VehicleOwner {
    @Id
    private String id;
    private String ownerName;
    private String ownerNationalIdCard;
    private String ownerEmail;
    private Date dateBirthDay;
    private String ownerPhoneNumber;
    private String ownerAddress;
    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicles = new ArrayList<>();
}
