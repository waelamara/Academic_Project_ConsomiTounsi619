package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.GestionProduit.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
