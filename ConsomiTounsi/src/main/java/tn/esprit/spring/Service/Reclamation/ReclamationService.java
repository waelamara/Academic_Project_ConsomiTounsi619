package tn.esprit.spring.Service.Reclamation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.reclamationRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Livreur.LivreurService;


@Service
public class ReclamationService {
	
	@Autowired
	reclamationRepository reclamationRepository;
	
	@Autowired
	UserService UserDAO;
	@Autowired
	CommandeRepository CommandeRepository;
	
	private static final Logger L =LogManager.getLogger(LivreurService.class);
	
	/* Ajouter une reclamation avec user id parametre*/
	public reclamation save(reclamation rec, long u) {
		User us = UserDAO.findOne(u);
		rec.setReponse(null);
		rec.setTraiter(false); 
		rec.setEtat("En_attente");
		rec.setUser(us);
		return reclamationRepository.save(rec);

	}
	/* Ajouter une reclamation sans user id parametre*/
	public reclamation save1(reclamation rec) {
		rec.setReponse(null);
		rec.setTraiter(false); 
		rec.setEtat("En_attente");
		return reclamationRepository.save(rec);

	}
	

	/* voir tous les reclamations */
	public List <reclamation> findall(){
		List<reclamation> a = reclamationRepository.findAll();
		
		for(reclamation reclamations : a)
		{
			L.info("reclamations :"+ reclamations);
			
		}
		return a;
		}
	/*Chercher ue reclamation*/
	public reclamation findbyid(long rec_id){
return reclamationRepository.getOne(rec_id);}
		
		
	
		/* Delete D"un Livreur*/
	public void delete (long id ){
		reclamationRepository.deleteById(id);}
	
	/*Update d'un reclamation*/
	public  reclamation updateLiv(reclamation rec)	{
		return reclamationRepository.save(rec);
		
	}
	
	
	/* traiter une reclamation */
	public reclamation traiter(reclamation rec) {
		
		return reclamationRepository.save(rec);

	}
	
	/*Determiner Liste des commandes pour les nikomou*/
	public List<Long> ListeCommandeparuser(Long iduser)
	{
		return CommandeRepository.ListeCommandePariduser(iduser);
		
	}
	
	

}
