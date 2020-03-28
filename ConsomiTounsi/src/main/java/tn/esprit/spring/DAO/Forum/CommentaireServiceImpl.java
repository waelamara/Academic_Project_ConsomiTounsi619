package tn.esprit.spring.DAO.Forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Commentaire;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.CommentarieRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;
@Service
public class CommentaireServiceImpl implements ICommentaireService{
	@Autowired
	CommentarieRepository commentairesRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SujetRepository sujetRepository;
	
	
	
	@Override
	public int ajouterCommentaire(Commentaire c, Long sujetId, Long userId) {
	 User user =userRepository.getOne(userId);
	 Sujet sujet=sujetRepository.getOne(sujetId);
	 c.setIdSujet(sujet);
	 c.setIdUser(user);
	 c.setNbDislike(0);
	 c.setNbLike(0);
	 commentairesRepository.save(c);
		return c.getId().intValue() ;
	}

	@Override
	public List <Commentaire> getCommentaire(Long sujetId, Long userId) {
		List<Commentaire> commentaires=new ArrayList<>();
		commentaires= commentairesRepository.getCommentaire(sujetId, userId);
		return commentaires;
	}
	@Override
	public Commentaire getCommentairebyId(Long comId) {
		return commentairesRepository.getOne(comId);
	}
	@Override
	public List<Commentaire> getCommentaireOfSujet(Long sujetId) {
		Sujet sujet= sujetRepository.findById(sujetId).get();
		List<Commentaire> commentaires=new ArrayList<>();
		for(Commentaire com : sujet.getCommentarie())
			commentaires.add(com);
		return commentaires;
				}

	@Override
	public int deletecommentairevoteById(Long comId,Long sujetId,Long userId) {
		Commentaire com = commentairesRepository.getOne(comId);
		if(com.getIdUser().getId()== userId && com.getIdSujet().getId()==sujetId)
		{
			commentairesRepository.deleteById(com.getId());	 
			 return 1;
		}
			 return 0;  
	}


	@Override
	public void modifierCommentaire(String desc,Long comId) {
		Commentaire com = commentairesRepository.getOne(comId);
		com.setDescription(desc);
		commentairesRepository.save(com);
	}

	@Override
	public String findNamebyCommentaire(Long comId) {
		Commentaire com = commentairesRepository.getOne(comId);
		String nom =com.getIdUser().getFirstName()+" "+com.getIdUser().getLastName();
		return nom;
	}

}
