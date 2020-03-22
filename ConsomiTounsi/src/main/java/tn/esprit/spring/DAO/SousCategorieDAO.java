package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.GestionProduit.SCategorie;
import tn.esprit.spring.Repository.SousCategorieRepository;

@Service
public class SousCategorieDAO {
	@Autowired
	SousCategorieRepository sousCategorieRepository;

	public SCategorie save(SCategorie c) {
		return sousCategorieRepository.save(c);
	}

	public List<SCategorie> findAll() {
		return sousCategorieRepository.findAll();
	}

	public SCategorie findOne(Long id) {
		return sousCategorieRepository.getOne(id);
	}

	public void Delete(SCategorie sc) {
		sousCategorieRepository.delete(sc);
	}
}
