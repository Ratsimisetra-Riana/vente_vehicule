package com.voiture.occasion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.voiture.occasion.model.Annonce;
import com.voiture.occasion.model.Image;
import com.voiture.occasion.repository.AnnonceRepository;
import com.voiture.occasion.repository.ImageRepository;

@Service
public class AnnonceService {
    @Autowired
    AnnonceRepository repository;

    @Autowired
    ImageRepository imageRepo;

    public Annonce createAnnonce(Annonce annonce)  {
        Annonce annc = repository.save(annonce);
        List<Image> images = annonce.getImages();
        for (Image image : images) {
            image.setAnnonce(annc);
        }
        imageRepo.saveAll(images);
        return annc;
    }    
    
    public Optional<Annonce> updateStatus(String id, String status) throws Exception {
        Optional<Annonce> annonce = repository.findById(id);
        if (annonce.isPresent()) {
            Annonce newStatus = annonce.get();
            newStatus.setStatus(status);
            repository.save(newStatus);
        } else {
            throw new Exception("Annonce is not present");
        }
        return annonce;
    }


    public List<Annonce> getByUtilisateur(String idUtilisateur) {
        return repository.findByUtilisateur(idUtilisateur);
    }

    public List<Annonce> findByUtilisateur(String idUtilisateur) {
        return repository.getByUtilisateur(idUtilisateur);
    }

    public Optional<Annonce> getByIdAnnonce(String idAnnonce) {
        return repository.findById(idAnnonce);
    }

    public Optional<Annonce> addFavoris(String idAnnonce, String idUtilisateur) {
        repository.saveFavoris(idAnnonce, idUtilisateur);
        return repository.findById(idAnnonce);
    }

    public List<Annonce> getFavoris(String idUtilisateur) {
        return repository.findFavoris(idUtilisateur);
    }

    public List<Annonce> getAllByUtilisateur(String idUtilisateur){
        return repository.getByUtilisateur(idUtilisateur);
    }

    public List<Annonce> getValidateByUtilisateur(String idUtilisateur){
        return repository.getValidateByUtilisateur(idUtilisateur);
    }

    public List<Annonce> getRefusedByUtilisateur(String idUtilisateur){
        return repository.getRefusedByUtilisateur(idUtilisateur);
    }

    public List<Annonce> getSoldByUtilisateur(String idUtilisateur){
        return repository.getSoldByUtilisateur(idUtilisateur);
    }

    public List<Annonce> getToValidateByUtilisateur(String idUtilisateur){
        return repository.getToValidateByUtilisateur(idUtilisateur);
    }


    // admin
    public Optional<Annonce> insertValidation(int etat, String idAnnonce) {
        repository.saveValidation(etat, idAnnonce);
        return repository.findById(idAnnonce);
    }

    public List<Annonce> getAllDemande() {
        return repository.findAllDemande();
    }
    
    // pubilc
    public List<Annonce> getAllValidate() {
        return repository.findAllValidate();
    }

    public List<Annonce> searchByElements(String description, String idCoprs, String idMarque, String idModele, String idMoteur,String idTransmission) {
        return repository.findByElements(description, idCoprs, idTransmission, idMoteur, idMarque, idModele);
    }

    public Optional<Annonce> findById(String id) {
        return repository.findById(id);
    }

}
