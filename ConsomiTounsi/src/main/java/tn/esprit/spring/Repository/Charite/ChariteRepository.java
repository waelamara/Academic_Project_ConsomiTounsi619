package tn.esprit.spring.Repository.Charite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Charite.Charite;


public interface ChariteRepository extends JpaRepository<Charite, Long>{
	
}
