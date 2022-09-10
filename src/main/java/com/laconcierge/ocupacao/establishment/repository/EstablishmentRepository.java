package com.laconcierge.ocupacao.establishment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.laconcierge.ocupacao.establishment.entity.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
    @Transactional
    @Modifying
    @Query("update Establishment establishment set establishment.maxCapacity = :maxCapacity where establishment.id = :id")
    void updateMaxCapacity(@Param("id") UUID id, @Param("maxCapacity") Integer maxCapacity);

    @Transactional
    @Modifying
    @Query("update Establishment establishment set establishment.busyCapacity = :busyCapacity where establishment.id = :id")
    void updateBusyCapacity(@Param("id") UUID id, @Param("busyCapacity") Integer maxCapacity);
}
