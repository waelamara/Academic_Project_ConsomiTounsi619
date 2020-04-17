package tn.esprit.spring.Service.Produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Repository.Produit.CategorieRepository;

@Service
public class CategorieServiceImpl implements ICategorieService {
	@Autowired
	CategorieRepository categorieRepository;
	
	public Categorie save(Categorie c) {
		return categorieRepository.save(c);
	}
	
	public List<Categorie> findAll() {
		return categorieRepository.findAll();
	}
	
	public void Delete(Long id) {
		Categorie c =findOne(id);
		 categorieRepository.delete(c);
	}
	
	public Categorie findOne(Long id) {
		return categorieRepository.getOne(id);
	}

	public Categorie Update(Categorie c, Long id) {
		Categorie c2 = findOne(id);
		c2.setNomCategorie(c.getNomCategorie());
		return categorieRepository.save(c2);
	}
	
}
