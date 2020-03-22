package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tn.esprit.spring.Model.Facture;

public interface FactureRepository extends JpaRepository<Facture, Long> {

}
