package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Produit.ImageProduit;

public interface ImagesProduitInterface extends JpaRepository<ImageProduit, Long> {

}
