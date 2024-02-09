package com.voiture.occasion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voiture.occasion.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{
    
}
