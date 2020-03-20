package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.Commande;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
