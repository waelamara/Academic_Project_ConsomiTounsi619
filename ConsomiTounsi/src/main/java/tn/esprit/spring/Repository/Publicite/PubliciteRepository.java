package tn.esprit.spring.Repository.Publicite;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface PubliciteRepository extends JpaRepository<Publicite, Long> {
	@Query(value = "SELECT COUNT(*) FROM user WHERE sexe='FEMME'", nativeQuery = true)
	public int  CountFemmeFromUser();
	@Query(value = "SELECT COUNT(*) FROM user WHERE sexe='HOMME'", nativeQuery = true)
	public int  CountHommeFromUser();
	@Query(value = "SELECT COUNT(*) FROM user ", nativeQuery = true)
	public int  CountALLUser();
	@Query(value = "SELECT COUNT(*) FROM user WHERE year(NOW())-year(daten) BETWEEN ?1 AND ?2", nativeQuery = true)
	public int  CountUserWithAgeBetwin(int ageDebut,int ageFin);
	@Query(value = "SELECT COUNT(*) FROM user WHERE sexe='FEMME' AND year(NOW())-year(daten) BETWEEN ?1 AND ?2", nativeQuery = true)
	public int  CountUserFemmeWithAgeBetwin(int ageDebut,int ageFin);
	@Query(value = "SELECT COUNT(*) FROM user WHERE sexe='HOMME' AND year(NOW())-year(daten) BETWEEN ?1 AND ?2", nativeQuery = true)
	public int  CountUserHommeWithAgeBetwin(int ageDebut,int ageFin);
}
