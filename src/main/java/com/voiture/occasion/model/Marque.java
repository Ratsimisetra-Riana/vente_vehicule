package com.voiture.occasion.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String idMarque;
    String nomMarque;

    @OneToMany(mappedBy = "marque", cascade = CascadeType.ALL) @JsonIgnore
    List<Modele> modeles;

    public Marque() {
    }
    
    public Marque(String idMarque) {
        this.idMarque = idMarque;
    }

    public String getIdMarque() { return idMarque; }

    public void setIdMarque(String idMarque) { this.idMarque = idMarque; }

    public String getNomMarque() { return nomMarque; }

    public void setNomMarque(String nomMarque) { this.nomMarque = nomMarque; }

    public List<Modele> getModeles() { return modeles; }

    public void setModeles(List<Modele> modeles) { this.modeles = modeles; }
    
}
