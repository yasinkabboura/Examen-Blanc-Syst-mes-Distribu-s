package com.example.radarqueryservice.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Radar {
    @Id
    private String radarId;
    private double longitude;
    private double latitude;
    private int maxSpeed;
    @OneToMany(mappedBy = "radar")
    private List<OverSpeedDetection> overSpeedDetections;
}
