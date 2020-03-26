package tn.esprit.spring.Repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.Commentaire;

public interface CommentarieRepository extends JpaRepository<Commentaire,Long> {
	
	@Query(value="select * from commentaire where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public Commentaire getCommentaire(Long sujetId, Long userId);

}
