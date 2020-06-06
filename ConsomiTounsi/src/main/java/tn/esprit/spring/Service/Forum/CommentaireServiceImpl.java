package tn.esprit.spring.Service.Forum;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.transaction.Transactional;

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
	
	@Transactional
	@Override
	public int ajouterCommentaire(Commentaire c, Long sujetId, Long userId) {
		List<String> badwords=new ArrayList<>();
		badwords.add("corona");
		badwords.add("badwors");
		badwords.add("bads");
		String motcommentaire[]=c.getDescription().split(" ");
		String com ="";
		 User user =userRepository.getOne(userId);
		 Sujet sujet=sujetRepository.getOne(sujetId);
	for(String mots:motcommentaire){
//		if(motcommentaire.length==1 && motcommentaire.equals("b"))
		
			if (badwords.contains(mots)){
			    mots="(@#à*&è)";
				com=com+" "+mots;	
			}
		else
			com=com+" "+mots;}
	LocalDateTime localDateTime = LocalDateTime.now();
    Date date =  Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
	c.setDateAjout(date);
	 c.setIdSujet(sujet);
	 c.setIdUser(user);
	 c.setNbDislike(0);
	 c.setNbLike(0);
	 c.setDescription(com);
	 commentairesRepository.save(c);
	return c.getId().intValue();
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
		
		return commentairesRepository.getCommentaire(sujetId);
				}

	@Override
	public int deletecommentairevoteById(Long comId,Long sujetId,Long userId) {
		Commentaire com = commentairesRepository.findById(comId).get();
		if(com.getIdUser().getId()== userId)
		{
			commentairesRepository.deleteById(com.getId());	 
			 return 1;
		}
			 return 0;  
	}


	@Override
	public void modifierCommentaire(String desc,Long comId) {
		Commentaire com = commentairesRepository.findById(comId).get();
		List<String> badwords=new ArrayList<>();
		badwords.add("bad");
		badwords.add("badwors");
		badwords.add("bads");
		String motcommentaire[]=desc.split(" ");
		String mot ="";
		for(String mots:motcommentaire){
			if (badwords.contains(mots)){
			    mots="(@#à*&è)";
				mot=mot+" "+mots;	
			}
		else
			mot=mot+" "+mots;}
		com.setDescription(mot);
		commentairesRepository.save(com);
	}

	@Override
	public String findNamebyCommentaire(Long comId) {
		Commentaire com = commentairesRepository.getOne(comId);
		String nom =com.getIdUser().getFirstName()+" "+com.getIdUser().getLastName();
		return nom;
	}

	@Override
	public int countNbcommentaire(Long sujetId) {
		return  commentairesRepository.countNbCommentaire(sujetId);
		
	}

	@Override
	public int RemoveCommentairebyIdUser(Long comId, Long userId) {
		Commentaire com = commentairesRepository.findById(comId).get();
		if(com.getIdUser().getId()== userId)
		{
			commentairesRepository.delete(com);	 
			 return 1;
		}
			 return 0;  
	}
	@Override
      public int verivcationCom(Long comId,Long userId){
  		Commentaire com = commentairesRepository.findById(comId).get();
  		if(com.getIdUser().getId()== userId)
		{
			 return 1;
		}
			 return 0;  
	}
 }

