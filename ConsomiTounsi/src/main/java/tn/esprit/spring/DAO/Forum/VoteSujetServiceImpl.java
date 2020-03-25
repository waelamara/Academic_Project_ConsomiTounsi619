package tn.esprit.spring.DAO.Forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.VoteSujet;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;
import tn.esprit.spring.Repository.Forum.VoteSujetRepository;
@Service
public class VoteSujetServiceImpl implements IVoteSujetService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private  SujetRepository sujetRepository ;
	@Autowired
	private  VoteSujetRepository voteSujetRepository ;
	
	@Override
	public int ajouterlike(VoteSujet v, Long sujetId, Long userId) {
   	 	v.setNbLike(1);
   		Sujet sujet = sujetRepository.findById(sujetId).get();
		User user= userRepository.findById(userId).get();
		v.setIdUser(user);
		v.setIdSujet(sujet);
		voteSujetRepository.save(v);
		return 0;
	}

	@Override
	public int ajouterdislike(VoteSujet v, Long sujetId, Long userId) {
		v.setNbDislike(1);
   		Sujet sujet = sujetRepository.findById(sujetId).get();
		User user= userRepository.findById(userId).get();
		v.setIdUser(user);
		v.setIdSujet(sujet);
		voteSujetRepository.save(v);
		return 0;
	}

	@Override
	public List<VoteSujet> getAllVote(Long sujetId, Long userId) {
		return voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
	}

	@Override
	public void deletevoteById(Long sujetId, Long userId) {
		 voteSujetRepository.deletevoteById(sujetId, userId);
	}

	@Override
	public void Updatelike( Long sujetId,Long userId) {
		 voteSujetRepository.Updatelike(sujetId,userId);
	
	}

	@Override
	public void Updatedislike(Long sujetId,Long userId) {
		 voteSujetRepository.Updatedislike(userId, sujetId);
	}

	@Override
	public Boolean verificationvote(Long userId, Long sujetId) {
		List<VoteSujet> vote=new ArrayList<VoteSujet>();
	  vote=voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
		if (vote.isEmpty())
			return false;
		return true;
	}

	@Override
	public int countlike(Long sujetId) {
		
		return  voteSujetRepository.countlike(sujetId);
	}

	@Override
	public int countdislik(Long sujetId) {
		
		return voteSujetRepository.countdislik(sujetId);
	}

	
}
