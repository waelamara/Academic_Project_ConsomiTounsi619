package tn.esprit.spring.DAO.Forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.CategorieSujetRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;

@Service
public class SujetServiceImpl implements ISujetService {
	@Autowired
	private  SujetRepository sujetRepository ;
	@Autowired
	private  CategorieSujetRepository categorieSujetRepository; 
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int ajouterSujet(Sujet s,Long categId,Long userId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		User user= userRepository.findById(userId).get();
		s.setCategorieSujet(categS);
		s.setIdUser(user);
		sujetRepository.save(s);
		return s.getId().intValue();	
	}

	@Override
	public List<Sujet> getAllSujets() {
		return sujetRepository.findAll();
	}

	@Override
	public int deleteSujetById(Long sujetId,Long userId) {
		Sujet sujet = sujetRepository.findById(sujetId).get();
		if(sujet.getIdUser().getId()== userId)
		{
			 sujetRepository.deleteById(sujetId);	 
			 return 1;
		}
			 return 0;  
	}

	@Override
	public List<Sujet> findSujetbyName(String name) {
		return  sujetRepository.findSujetbyName(name);
	}

	@Override
	public Sujet findOne(Long id) {

		return sujetRepository.getOne(id);
	}

	@Override
	public void modifierDescription(String desc, Long sujetId ) {
	Sujet sujet =sujetRepository.findById(sujetId).get();
		sujet.setDescription(desc);
		sujetRepository.save(sujet);
		
	}

	@Override
	public void affecterSujetACategS(Long sujId, Long categId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		Sujet sujet=sujetRepository.findById(sujId).get();
		sujet.setCategorieSujet(categS);
		sujetRepository.save(sujet);	
	}

	@Override
	public List<String> getAllSujetNamesByCategorie(Long categId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		List<String> sujetNames = new ArrayList<>();
		for(Sujet suj : categS.getSujets())
			sujetNames.add(suj.getNomSujet());
		return sujetNames;
	}

	@Override
	public List<Sujet> findSujetbyUser(Long userid) {
		User user = userRepository.findById(userid).get();
		List<Sujet> sujets = new ArrayList<>();
		for(Sujet suj :user.getSujets())
			sujets.add(suj);
		return sujets;
	}

}
