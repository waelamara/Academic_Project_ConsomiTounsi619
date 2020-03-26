package tn.esprit.spring.Repository.Forum;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.VoteSujet;

public interface VoteSujetRepository extends JpaRepository<VoteSujet, Long> {
	@Query(value = "select count(*) from vote_sujet where nb_like=1 and id_sujet= ?1", nativeQuery = true)
	 public int countlike(Long sujetId);
	@Query(value = "select count(*) from vote_sujet where nb_dislike=1 and id_sujet= ?1", nativeQuery = true)
	 public int countdislik(Long sujetId);
	@Query(value="select * from vote_sujet where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public VoteSujet getVoteBySujetAndUser(Long sujetId, Long userId);
}