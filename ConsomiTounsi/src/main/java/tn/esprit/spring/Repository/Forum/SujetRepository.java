package tn.esprit.spring.Repository.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.*;

public interface SujetRepository extends JpaRepository<Sujet, Long> {
	@Query(value = "SELECT DISTINCT * FROM sujet WHERE nom_sujet LIKE ?1%", nativeQuery = true)
	public List<Sujet> findSujetbyName(String name);

	@Query(value = "SELECT DISTINCT * FROM sujet ORDER BY nb_like Desc,date_ajout DESC", nativeQuery = true)
	public List<Sujet> findAllOrderbyDate();

	@Query(value = "SELECT * FROM sujet WHERE id_categorie_sujet= ?1 ORDER BY nb_like Desc,date_ajout DESC ", nativeQuery = true)
	public List<Sujet> findSujetbycatgID(Long categId);

	
	@Query(value="SELECT count(*) FROM `sujet` WHERE `id_categorie_sujet`=?1",nativeQuery=true)
	public int countSujetbycatId(Long categId);
	
	////Raed
	@Query(value = "SELECT cs.nom_categorie, count(*) AS nb_suj FROM sujet s JOIN categorie_sujet cs ON s.id_categorie_sujet=cs.id GROUP BY cs.id LIMIT 10", nativeQuery = true)
	public List<Object[]> SujetByCategoty();

}