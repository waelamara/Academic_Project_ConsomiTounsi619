package tn.esprit.spring.DAO.Forum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Repository.Forum.CategorieSujetRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;

@Service
public class SujetServiceImpl implements ISujetService {
	@Autowired
	private  SujetRepository sujetRepository ;
	@Autowired
	private  CategorieSujetRepository categorieSujetRepository; 
	
	@Override
	public int ajouterSujet(Sujet s) {
		sujetRepository.save(s);
		return s.getId().intValue();	
	}

	@Override
	public List<Sujet> getAllSujets() {
		return sujetRepository.findAll();
	}

	@Override
	public void deleteSujetById(long sujetId) {
	   sujetRepository.deleteById(sujetId);
		
	}

	@Override
	public List<Sujet> findSujetbyName(String name) {
		return  sujetRepository.findSujetbyName(name);
	}

	@Override
	public Sujet findOne(Long id) {

		return   sujetRepository.getOne(id);
	}

	@Override
	public void modifierDescription(String desc, long sujetId) {
	Sujet sujet =sujetRepository.findById(sujetId).get();
		sujet.setDescription(desc);
		sujetRepository.save(sujet);
		
	}

	@Override
	public void affecterSujetACategS(long sujId, long categId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		Sujet sujet=sujetRepository.findById(sujId).get();
		sujet.setCategorieSujet(categS);
		sujetRepository.save(sujet);	
	}

	@Override
	public List<String> getAllSujetNamesByCategorie(long categId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		List<String> sujetNames = new ArrayList<>();
		for(Sujet suj : categS.getSujets())
			sujetNames.add(suj.getNomSujet());
		return sujetNames;
	}

}
