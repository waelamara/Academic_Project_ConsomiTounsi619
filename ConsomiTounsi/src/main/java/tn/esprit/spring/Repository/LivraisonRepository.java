package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Model.Livraison;

public interface LivraisonRepository extends JpaRepository <Livraison, Long> {

}
