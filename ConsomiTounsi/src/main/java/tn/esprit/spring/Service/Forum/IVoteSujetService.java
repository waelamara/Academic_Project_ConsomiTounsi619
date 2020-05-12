package tn.esprit.spring.Service.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.VoteSujet;


public interface IVoteSujetService {

	public int ajouterlike(VoteSujet v,Long sujetId,Long userId);
	public int ajouterdislike(VoteSujet v,Long sujetId,Long userId);
	public VoteSujet getVote(Long sujetId,Long userId);
	public List<VoteSujet> getVoteOfSujet(Long sujetId);
	public void deletevoteById(Long sujetId,Long userId);
	public void Updatelike(Long sujetId, Long userId);
	public void Updatedislike(Long sujetId,Long userId);
    public Boolean verificationvote (Long sujetId,Long userId);
    public int verificationvoteChoix(Long userId, Long sujetId) ;
    public int countlike(Long sujetId);    
    public int countdislik(Long sujetId);
    public List<String> findNomdesUsersVoter(Long sujetId);
    public void affecterdespoints(Long sujetId);
}
