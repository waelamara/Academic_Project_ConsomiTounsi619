package tn.esprit.spring.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.reclamation;
import tn.esprit.spring.Repository.reclamationRepository;


@Service
public class ReclamationDAO {
	
	@Autowired
	reclamationRepository reclamationRepository;
	
	private static final Logger L =LogManager.getLogger(LivreurDAO.class);
	
	/* Ajouter une reclamation */
	public reclamation save(reclamation rec) {
		return reclamationRepository.save(rec);

	}
	

	/* voir tous les livreurs */
	public List <reclamation> findall(){
		List<reclamation> a = reclamationRepository.findAll();
		
		for(reclamation reclamations : a)
		{
			L.info("reclamations :"+ reclamations);
			
		}
		return a;
		}
	/*Chercher un livreur*/
//	public Livreur findOne(Integer liv){
//		return livreurRepository.getOne(liv);}
		
		
	
		/* Delete D"un Livreur*/
	public void delete (long id ){
		reclamationRepository.deleteById(id);}
	
	/*Update d'un Livreur*/
	public  reclamation updateLiv(reclamation rec)	{
		return reclamationRepository.save(rec);
		
	}
	
	

}
