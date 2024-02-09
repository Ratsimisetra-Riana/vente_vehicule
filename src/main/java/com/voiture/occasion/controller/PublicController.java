package com.voiture.occasion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.voiture.occasion.model.Annonce;
import com.voiture.occasion.model.Utilisateur;
import com.voiture.occasion.service.AnnonceService;
import com.voiture.occasion.service.UtilisateurService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.voiture.occasion.model.Annonce;
import com.voiture.occasion.model.Corps;
import com.voiture.occasion.model.Moteur;
import com.voiture.occasion.model.Statistique;
import com.voiture.occasion.model.Transmission;
import com.voiture.occasion.model.Marque;
import com.voiture.occasion.model.Modele;
import com.voiture.occasion.repository.CorpsRepository;
import com.voiture.occasion.repository.MoteurRepository;
import com.voiture.occasion.repository.TransmissionRepository;
import com.voiture.occasion.repository.MarqueRepository;
import com.voiture.occasion.repository.ModeleRepository;

@CrossOrigin(origins = "*")
@RestController
public class PublicController {
    @Autowired
    private AnnonceService service;
    @Autowired
    private UtilisateurService userRep;
    
    @GetMapping("/public/annonces")
    public List<Annonce> getALlValidates() {
        return service.getAllValidate();
    }

    @GetMapping("/public/recherche")
    public List<Annonce> getRecherche(
        @RequestParam(name = "mots") String description,
        @RequestParam(name = "idCorps") String idCoprs,
        @RequestParam(name = "idMarque") String idMarque,
        @RequestParam(name = "idModele") String idModele,
        @RequestParam(name = "idMoteur") String idMoteur,
        @RequestParam(name = "idTransmission") String idTransmission
    ) {
        return service.searchByElements(description, idCoprs, idTransmission, idMoteur, idMarque, idModele);
    }

    @PostMapping("/public/signup")
    public Utilisateur signUp(@RequestBody Utilisateur utilisateur) {
        return userRep.save(utilisateur);
    }

    @PostMapping("/public/signin")
    public String[] signIn(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        String[] result = userRep.login(email, password);
        if (result != null) {
            return result;
        }
        return null;
    }

    
}
