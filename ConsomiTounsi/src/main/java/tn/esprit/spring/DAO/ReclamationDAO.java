package tn.esprit.spring.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.reclamationRepository;


@Service
public class ReclamationDAO {
	
	@Autowired
	reclamationRepository reclamationRepository;
	
	@Autowired
	UserDAO UserDAO;
	
	private static final Logger L =LogManager.getLogger(LivreurDAO.class);
	
	/* Ajouter une reclamation */
	public reclamation save(reclamation rec, long u) {
		User us = UserDAO.findOne(u);
		rec.setReponse(null);
		rec.setTraiter(false); 
		rec.setEtat("En_attente");
		rec.setUser(us);
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
	
	

}
