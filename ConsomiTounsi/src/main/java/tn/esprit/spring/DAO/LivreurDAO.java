package tn.esprit.spring.DAO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Repository.LivreurRepository;


@Service
public class LivreurDAO {

	@Autowired
	LivreurRepository livreurRepository;
	
	private static final Logger L =LogManager.getLogger(LivreurDAO.class);

	/* Ajouter un livreur */
	public Livreur save(Livreur liv) {
		return livreurRepository.save(liv);

	}

	/* voir tous les livreurs */
	public List <Livreur> findall(){
		List<Livreur> a = livreurRepository.findAll();
		
		for(Livreur Livreurs : a)
		{
			L.info("Livreurs :"+ Livreurs);
			
		}
		return a;
		}
	/*Chercher un livreur*/
//	public Livreur findOne(Integer liv){
//		return livreurRepository.getOne(liv);}
		
		
	
		/* Delete D"un Livreur*/
	public void delete (long id ){
		livreurRepository.deleteById(id);}
	
	/*Update d'un Livreur*/
	public  Livreur updateLiv(Livreur Liv)	{
		return livreurRepository.save(Liv);
		
	}
	
	
			
		
	
	}


