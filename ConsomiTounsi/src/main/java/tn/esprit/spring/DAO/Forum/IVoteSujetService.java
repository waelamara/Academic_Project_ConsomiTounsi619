package tn.esprit.spring.DAO.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.VoteSujet;


public interface IVoteSujetService {

	public int ajouterlike(VoteSujet v,Long sujetId,Long userId);
	public int ajouterdislike(VoteSujet v,Long sujetId,Long userId);
	public List<VoteSujet> getAllVote(Long sujetId,Long userId);
	public void deletevoteById(Long sujetId,Long userId);
	public void Updatelike(Long userId, Long sujetId);
	public void Updatedislike(Long sujetId,Long userId);
    public Boolean verificationvote (Long sujetId,Long userId);
    public int countlike(Long sujetId);    
    public int countdislik(Long sujetId);

}
