package tn.esprit.spring.Repository.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tn.esprit.spring.Model.Forum.Sujet;

public interface SujetRepository extends JpaRepository<Sujet, Long> {
	@Query(value = "SELECT DISTINCT * FROM sujet WHERE nom_sujet LIKE ?1%", nativeQuery = true)
	public List<Sujet> findSujetbyName(String name);
	@Query(value = "SELECT DISTINCT * FROM sujet ORDER BY date_ajout Desc", nativeQuery = true)
	public List<Sujet> findAllOrderbyDate();
	@Query(value = "SELECT * FROM sujet WHERE id_categorie_sujet= ?1 ORDER BY date_ajout Desc", nativeQuery = true)
public List<Sujet> findSujetbycatgID(Long categId);
}