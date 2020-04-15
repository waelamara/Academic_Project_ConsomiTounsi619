package tn.esprit.spring.Service.Forum;


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
	public VoteSujet getVote(Long sujetId, Long userId) {
		return voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
	}
	
	@Override
	public List<VoteSujet> getVoteOfSujet(Long sujetId) {
		Sujet sujet = sujetRepository.findById(sujetId).get();
		List<VoteSujet> votes = new ArrayList<>();
		for(VoteSujet v :sujet.getVotesSujet())
			votes.add(v);
			return votes;
	}


	@Override
	public void deletevoteById(Long sujetId, Long userId) {
		VoteSujet v=voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
		v.setNbLike(0);
		v.setNbDislike(0);
	voteSujetRepository.save(v);
	}

	@Override
	public void Updatelike( Long sujetId,Long userId) {
		VoteSujet v=voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
		v.setNbLike(1);
		v.setNbDislike(0);
		voteSujetRepository.save(v);
	
	}

	@Override
	public void Updatedislike(Long sujetId,Long userId) {
		VoteSujet v=voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
		v.setNbLike(0);
		v.setNbDislike(1);
		voteSujetRepository.save(v);
	}

	@Override
	public Boolean verificationvote(Long userId, Long sujetId) {
	 List <VoteSujet> votes=new ArrayList<>();
	 VoteSujet v=voteSujetRepository.getVoteBySujetAndUser(sujetId, userId);
		 votes.add(v);
		if (votes.isEmpty())
			return false;
		return true;
	}

	@Override
	public int countlike(Long sujetId) {
		return  voteSujetRepository.countlike(sujetId);
	}

	@Override
	public int countdislik(Long sujetId) {
		return voteSujetRepository.countdislike(sujetId);
	}

	@Override
	public List<String> findNomdesUsersVoter(Long sujetId) {
		Sujet sujet=sujetRepository.findById(sujetId).get();	
		List<String> noms = new ArrayList<>();
		for(VoteSujet v :sujet.getVotesSujet())
			noms.add(v.getIdUser().getFirstName()+" "+v.getIdUser().getLastName());
			return noms;	
	}
	
	@Override
	public void affecterdespoints(Long sujetId){
		Sujet sujet=sujetRepository.findById(sujetId).get();
		User user =userRepository.findById(sujet.getIdUser().getId()).get();
		int x=sujet.getNbLike();
		int diffLike,i=0,inter;
		int nbpoint=-1;
		int nblike=voteSujetRepository.countlike(sujetId);
		sujet.setNbLike(nblike);
		int nbdislike=voteSujetRepository.countdislike(sujetId);
		sujet.setNbDislike(nbdislike);
		sujetRepository.save(sujet);
		diffLike=sujet.getNbpoint()+(nblike-x);
		int nbpointFd=user.getPointFidelite();
		if(nblike-x>0){
		inter=diffLike;
		while(inter!=0){
		i++;
		inter--;
		if(diffLike%4==0){
		nbpoint=0;
		nbpointFd=nbpointFd+1;}	
		else if (i==4){
		nbpointFd=nbpointFd+1;
		i=0;}
		else{
		nbpoint=i;}
		}
		}
		user.setPointFidelite(nbpointFd);
		sujet.setNbpoint(nbpoint);
		sujetRepository.save(sujet);
		userRepository.save(user);
	}
	
}
