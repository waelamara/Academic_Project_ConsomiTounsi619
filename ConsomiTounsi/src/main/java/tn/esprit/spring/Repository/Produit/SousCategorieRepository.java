package tn.esprit.spring.Repository.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Produit.SCategorie;

public interface SousCategorieRepository extends JpaRepository<SCategorie, Long> {

}
