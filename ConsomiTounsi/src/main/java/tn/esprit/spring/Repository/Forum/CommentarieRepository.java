package tn.esprit.spring.Repository.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.Commentaire;

public interface CommentarieRepository extends JpaRepository<Commentaire,Long> {
	
	@Query(value="select * from commentaire where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public List<Commentaire> getCommentaire(Long sujetId, Long userId);
	
     @Query(value = "select count(*) from commentaire where id_sujet= ?1", nativeQuery = true)
	 public int countNbCommentaire(Long sujetId);

}
