package com.azmain.dcs.repository;

import com.azmain.dcs.model.DocumentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypesRepository extends JpaRepository<DocumentTypes,Long> {
}
