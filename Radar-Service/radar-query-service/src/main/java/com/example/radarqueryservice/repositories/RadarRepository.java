package com.example.radarqueryservice.repositories;

import com.example.radarqueryservice.entities.Radar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRepository extends JpaRepository<Radar,String> {
}
