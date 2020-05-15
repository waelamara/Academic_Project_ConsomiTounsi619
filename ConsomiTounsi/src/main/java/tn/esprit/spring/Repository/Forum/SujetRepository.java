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

	@Query(value = "SELECT id_categorie_sujet,count(*) as nb FROM sujet GROUP by id_categorie_sujet order by nb desc", nativeQuery = true)
	public List<NbSujetbyCat> countSujetbycatId();
}