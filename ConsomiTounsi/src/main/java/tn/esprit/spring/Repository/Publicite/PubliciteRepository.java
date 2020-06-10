package tn.esprit.spring.Repository.Publicite;


import java.util.Date;
import java.util.List;

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
	@Query(value = "SELECT * FROM publicite WHERE nom LIKE ?1%", nativeQuery = true)
	public List<Publicite> findLikeName(String nom);
	@Query(value = "SELECT * FROM publicite WHERE canal LIKE ?1", nativeQuery = true)
	public List<Publicite> findByCanal(String canal);
	@Query(value = "SELECT * FROM publicite WHERE canal LIKE ?1 and nom LIKE ?2%", nativeQuery = true)
	public List<Publicite> findByCanalAndName(String canal,String nom);
	@Query(value = "SELECT * FROM publicite WHERE gender_cible='TOUS' and debut_age_cible=10 and fin_age_cible=70 and status=1", nativeQuery = true)
	public List<Publicite> findPubForAll();
	@Query(value = "SELECT * FROM publicite WHERE year(NOW())-year(?1) BETWEEN debut_age_cible AND fin_age_cible and gender_cible=?2 and status=1", nativeQuery = true)
	public List<Publicite> getPubForUserConnecter(Date UserDateNaissance,String gender);
	@Query(value = "SELECT * FROM publicite WHERE NOW()>date_fin and status=1", nativeQuery = true)
	public List<Publicite> findPubFinished();
}
