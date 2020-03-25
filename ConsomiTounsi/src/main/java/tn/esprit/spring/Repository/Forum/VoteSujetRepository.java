package tn.esprit.spring.Repository.Forum;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tn.esprit.spring.Model.Forum.VoteSujet;

public interface VoteSujetRepository extends JpaRepository<VoteSujet, Long> {
	@Query(value = "select count(*) from vote_sujet where nb_like=1 and id_sujet= ?1", nativeQuery = true)
	 public int countlike(Long sujetId);
	@Query(value = "select count(*) from vote_sujet where nb_dislike=1 and id_sujet= ?1", nativeQuery = true)
	 public int countdislik(Long sujetId);
	@Query(value = "UPDATE `vote_sujet` SET `nb_like`=1,`nb_dislike`=0 WHERE id_sujet=?1 AND id_user=?2", nativeQuery = true)
	public void Updatelike(Long sujetId,Long userId);
	@Query(value = "UPDATE `vote_sujet` SET `nb_like`=0,`nb_dislike`=1 WHERE id_sujet=?1 AND id_user=?2", nativeQuery = true)
	public void Updatedislike( Long sujetId,Long userId);
	@Query(value ="UPDATE `vote_sujet` SET `nb_like`=0,`nb_dislike`=0 WHERE id_sujet=?1 AND id_user=?2",nativeQuery = true)
	public void deletevoteById(Long sujetId, Long userId);
	@Query(value="select * from vote_sujet where id_sujet=?1 AND id_user=?2",nativeQuery=true)
	public List<VoteSujet> getVoteBySujetAndUser(Long sujetId, Long userId);
}