package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.GestionProduit.SCategorie;

public interface SousCategorieRepository extends JpaRepository<SCategorie, Long> {

}
