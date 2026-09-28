package com.clearflow.settlement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearflow.settlement.entity.IngestionRequestEntity;

public interface IngestionRepository extends JpaRepository<IngestionRequestEntity, UUID> {

    Optional<IngestionRequestEntity> findByRequestIdAndSourceSystem(String requestId, String sourceSystem);

}
