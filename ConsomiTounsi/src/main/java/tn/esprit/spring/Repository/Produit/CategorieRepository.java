package tn.esprit.spring.Repository.Produit;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Produit.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
