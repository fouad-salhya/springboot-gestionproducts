package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.entities.CommandeEntity;

@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, Long>{

}
