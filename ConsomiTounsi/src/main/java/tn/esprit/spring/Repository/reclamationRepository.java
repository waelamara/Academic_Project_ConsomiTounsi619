package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.reclamation;



public interface reclamationRepository extends JpaRepository <reclamation, Long> {
	
	/* Change Etat Reclamation */
	@Modifying
	@Query(value = "UPDATE `reclamation` SET `etat`=?1,`traiter`=1 WHERE `id`=?2", nativeQuery = true)
	public void ChangetEtat(String Dispo, long id);
	
	/*Repondre au reclamation*/
	@Modifying
	@Query(value = "UPDATE `reclamation` SET `reponse`=?1 ,`traiter`=1 WHERE `id`=?2", nativeQuery = true)
	public void RepondreReclamation(String reponse1, long id);

}
