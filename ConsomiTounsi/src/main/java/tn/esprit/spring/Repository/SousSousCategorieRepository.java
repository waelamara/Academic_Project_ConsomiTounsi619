package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.GestionProduit.SsCategorie;

public interface SousSousCategorieRepository extends JpaRepository<SsCategorie, Long> {

}
