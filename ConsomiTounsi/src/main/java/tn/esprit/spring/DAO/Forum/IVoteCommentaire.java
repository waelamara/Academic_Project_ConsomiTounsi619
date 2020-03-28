package tn.esprit.spring.DAO.Forum;

import java.util.List;

import tn.esprit.spring.Model.Forum.Vote;

public interface IVoteCommentaire {

	public int ajouterlike(Vote v,Long comId,Long userId);
	public int ajouterdislike(Vote v,Long comId,Long userId);
	public Vote getVote(Long comId,Long userId);
	public List<Vote> getVoteOfCom(Long comId);
	public void deletevoteById(Long comId,Long userId);
	public void Updatelike(Long comId, Long userId);
	public void Updatedislike(Long comId,Long userId);
    public Boolean verificationvote (Long comId,Long userId);
    public int countlikeCom(Long comId);    
    public int countdislikCom(Long comId);
    public List<String> findNomdesUsersVoter(Long comId);
}
