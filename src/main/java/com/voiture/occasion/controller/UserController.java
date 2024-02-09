package com.voiture.occasion.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voiture.occasion.config.auth.TokenProvider;
import com.voiture.occasion.model.Annonce;
import com.voiture.occasion.service.AnnonceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class UserController {
    @Autowired
    private AnnonceService service;
    @Autowired
    private ModeleRepository modeleRep;
    @Autowired
    private MarqueRepository marqueRep;
    @Autowired
    private CorpsRepository corpsRep;
    @Autowired
    private MoteurRepository moteurRep;
    @Autowired
    private TransmissionRepository transmissionRep;
    @Autowired
    private TokenProvider token;


    @PostMapping(value = "/user/annonce")
    public Annonce createAnnonce(@RequestHeader(name = "Authorization") String utilisateur,  @RequestBody Annonce annonce) { 
            String idUtilisateur = token.validateToken(utilisateur.replace("Bearer ", ""));
            annonce.setIdUtilisateur(idUtilisateur);
            annonce.setStatus("0");
            annonce.setDateAnnonce(LocalDateTime.now());
            return service.createAnnonce(annonce);
    }



    @GetMapping("/user/annonces")
    public List<Annonce> getByUtilisateur(@RequestHeader(name = "Authorization") String utilisateur) {
        String idUtilisateur = token.validateToken(utilisateur.replace("Bearer ", ""));
        return service.getByUtilisateur(idUtilisateur);
    }

    @GetMapping("/user/historiques")
    public List<Annonce> getHistorique(@RequestHeader(name = "Authorization") String utilisateur) {
        String idUtilisateur = token.validateToken(utilisateur.replace("Bearer ", ""));
        return service.getByUtilisateur(idUtilisateur);
    }

    @PutMapping("/user/annonce/{id}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable("id") String id, @PathVariable(name = "status") String status) {
        try { service.updateStatus(id, status); } 
        catch (Exception e) { return ResponseEntity.ok("Error update"); }        
        return ResponseEntity.ok("Column update successfully");
    }

    @GetMapping("/user/annonce/{id}")
    public Optional<Annonce> getByIdAnnonce(@PathVariable("id") String id) {
        return service.getByIdAnnonce(id);
    }
    

    @PostMapping("/user/favoris/{id}")
    public Optional<Annonce> addFavoris(@PathVariable("id") String id, @RequestHeader(name = "Authorization") String utilisateur)  {
        String idUtilisateur = token.validateToken(utilisateur.replace("Bearer ", ""));
        return service.addFavoris(id, idUtilisateur); 
    }
    
    @GetMapping("/user/favoris")
    public List<Annonce> getFavoris(@RequestHeader(name = "Authorization") String utilisateur) {
        String idUtilisateur = token.validateToken(utilisateur.replace("Bearer ", ""));
        return service.getFavoris(idUtilisateur);
    }

    @GetMapping("/user/corps")
    public @ResponseBody List<Corps> getAllCorps() {
        return corpsRep.findAll();
    }

 @GetMapping("/user/marques")
    public @ResponseBody List<Marque> getAllMarque() {
        return marqueRep.findAll();
    }

@GetMapping("/user/modeles")
    public @ResponseBody List<Modele> getAllModele() {
        return modeleRep.findAll();
    }

 @GetMapping("/user/moteurs")
    public @ResponseBody List<Moteur> getAllMoteur() {
        return moteurRep.findAll();
    } 

    @GetMapping("/user/transmissions")
    public @ResponseBody List<Transmission> getAllTransmission() {
        return transmissionRep.findAll();
    }
}
