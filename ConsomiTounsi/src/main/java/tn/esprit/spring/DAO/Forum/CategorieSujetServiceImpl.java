package tn.esprit.spring.DAO.Forum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Repository.CategorieSujetRepository;

@Service
public class CategorieSujetServiceImpl implements ICategorieSujetService {
	@Autowired
	private  CategorieSujetRepository categorieSujetRepository; 
	
	@Override
	public int ajouterCategorieSujet(CategorieSujet c) {
		categorieSujetRepository.save(c);
		return 0;
	}

	@Override
	public List<CategorieSujet> getAllCategorieSujets() {
		return categorieSujetRepository.findAll();
		
	}

	@Override
	public void deleteCategorieSujetById(long CategId) {
		categorieSujetRepository.deleteById(CategId);
		
	}

	@Override
	public List<CategorieSujet> findCategbyName(String name) {
		return categorieSujetRepository.findCategbyName(name);
	}


	

}
