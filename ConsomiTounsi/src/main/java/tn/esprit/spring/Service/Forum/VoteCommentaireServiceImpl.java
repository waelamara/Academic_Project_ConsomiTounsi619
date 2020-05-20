package tn.esprit.spring.Service.Forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.CommentarieRepository;
import tn.esprit.spring.Repository.Forum.VoteCommentaireRepository;

@Service
public class VoteCommentaireServiceImpl implements IVoteCommentaire{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  CommentarieRepository commentaireRepository ;
	@Autowired
	private  VoteCommentaireRepository voteComRepository ;
	@Override
	public int ajouterlike(Vote v, Long comId, Long userId) {
		v.setNbLike(1);
		v.setNbDislike(0);
   		Commentaire com = commentaireRepository.findById(comId).get();
		User user= userRepository.findById(userId).get();
		v.setIdUser(user);
		v.setIdCommentaire(com);;
		voteComRepository.save(v);
		return 0;
	}
	@Override
	public int ajouterdislike(Vote v, Long comId, Long userId) {
		v.setNbDislike(1);
		v.setNbLike(0);
   		Commentaire com = commentaireRepository.findById(comId).get();
		User user= userRepository.findById(userId).get();
		v.setIdUser(user);
		v.setIdCommentaire(com);;
		voteComRepository.save(v);
		return 0;
	}
	@Override
	public Vote getVote(Long comId, Long userId) {
		return voteComRepository.getVoteByComAndUser(comId, userId);
	}
	@Override
	public List<Vote> getVoteOfCom(Long comId) {
	    Commentaire com =commentaireRepository.findById(comId).get();
		List<Vote> votes = new ArrayList<>();
		for(Vote v :com.getVotes())
			votes.add(v);
			return votes;
	}
	@Override
	public void deletevoteById(Long comId, Long userId) {
		Vote v=voteComRepository.getVoteByComAndUser(comId, userId);
		v.setNbLike(0);
		v.setNbDislike(0);
		voteComRepository.save(v);
		
	}
	@Override
	public void Updatelike(Long comId, Long userId) {
		Vote v=voteComRepository.getVoteByComAndUser(comId, userId);
		v.setNbLike(1);
		v.setNbDislike(0);
		voteComRepository.save(v);
		
	}
	@Override
	public void Updatedislike(Long comId, Long userId) {
		Vote v=voteComRepository.getVoteByComAndUser(comId, userId);
		v.setNbLike(0);
		v.setNbDislike(1);
		voteComRepository.save(v);
		
	}
	@Override
	public Boolean verificationvote(Long comId, Long userId) {
		 Vote v=voteComRepository.getVoteByComAndUser(comId, userId);
			if (v==null)
				return false;
			return true;
	}
	@Override
	public int countlikeCom(Long comId) {
		return voteComRepository.countlike(comId);
	}
	@Override
	public int countdislikCom(Long comId) {
		return voteComRepository.countdislik(comId);
	}
	@Override
	public List<String> findNomdesUsersVoter(Long comId) {
		Commentaire com=commentaireRepository.findById(comId).get();	
		List<String> noms = new ArrayList<>();
		for(Vote v :com.getVotes())
			noms.add(v.getIdUser().getFirstName()+" "+v.getIdUser().getLastName());
			return noms;	
	
	}
	
}