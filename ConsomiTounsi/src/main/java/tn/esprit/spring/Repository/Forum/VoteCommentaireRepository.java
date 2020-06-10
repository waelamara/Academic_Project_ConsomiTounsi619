package tn.esprit.spring.Repository.Forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Forum.Vote;




public interface VoteCommentaireRepository extends JpaRepository<Vote, Long> {
	@Query(value = "select count(*) from vote where nb_like=1 and id_commentaire= ?1", nativeQuery = true)
	 public int countlike(Long comId);
	@Query(value = "select count(*) from vote where nb_dislike=1 and id_commentaire= ?1", nativeQuery = true)
	 public int countdislik(Long comId);
	@Query(value="select * from vote where id_commentaire=?1 AND id_user=?2",nativeQuery=true)
	public Vote getVoteByComAndUser(Long comId, Long userId);
	@Query(value="select * from vote where id_commentaire=?1 AND id_user=?2 and nb_like=1",nativeQuery=true)
	public Vote getVoteByComAndUserlike(Long comId, Long userId);
	@Query(value="select * from vote where id_commentaire=?1 AND id_user=?2 and nb_dislike=1",nativeQuery=true)
	public Vote getVoteBycomAndUserdislike(Long comId, Long userId);
}


