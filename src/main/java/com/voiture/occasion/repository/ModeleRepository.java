package com.voiture.occasion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.voiture.occasion.model.Modele;

@Repository
public interface ModeleRepository extends JpaRepository<Modele, String> {
    @Query(value = "select * from modele where id_marque = :idmarque", nativeQuery = true)
    public List<Modele> findByIdMarque(@Param("idmarque") String idMarque); 
}
