package tn.esprit.spring.Repository.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Produit.ImageProduit;

public interface ImagesProduitRepository extends JpaRepository<ImageProduit, Long> {

}
