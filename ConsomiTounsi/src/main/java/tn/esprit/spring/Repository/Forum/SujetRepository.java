package tn.esprit.spring.Repository.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tn.esprit.spring.Model.Forum.Sujet;

public interface SujetRepository extends JpaRepository<Sujet, Long> {
	@Query(value = "SELECT DISTINCT * FROM sujet WHERE nom_sujet LIKE ?1%", nativeQuery = true)
	public List<Sujet> findSujetbyName(String name);
}
