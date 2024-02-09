package com.voiture.occasion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "annonce_images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idImage; 
    String path;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "id_annonce") @JsonIgnore
    Annonce annonce;
    
    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }

    public Image(int idImage, String path, Annonce annonce) {
        this.idImage = idImage;
        this.path = path;
        this.annonce = annonce;
    }

    public int getIdImage() { return idImage; }

    public void setIdImage(int idImage) { this.idImage = idImage; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public Annonce getAnnonce() { return annonce; }

    public void setAnnonce(Annonce annonce) { this.annonce = annonce; }
}
