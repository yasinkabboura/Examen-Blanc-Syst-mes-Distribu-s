package com.example.infractionqueryservice.repositories;

import com.example.infractionqueryservice.entities.Infraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfractionRepository extends JpaRepository<Infraction,String> {
    Page<Infraction> findAllByOwnerNationalCardId(String ncID, Pageable pageable);
}
