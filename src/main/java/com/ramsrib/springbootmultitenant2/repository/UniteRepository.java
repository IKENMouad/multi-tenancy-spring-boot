package com.ramsrib.springbootmultitenant2.repository;

import com.ramsrib.springbootmultitenant2.model.Unite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UniteRepository extends JpaRepository<Unite, Long>   {
    
    Unite getByLabel(String label);


}
