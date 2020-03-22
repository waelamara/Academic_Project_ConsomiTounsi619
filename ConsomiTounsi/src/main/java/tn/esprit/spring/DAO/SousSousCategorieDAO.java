package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.GestionProduit.SsCategorie;
import tn.esprit.spring.Repository.SousSousCategorieRepository;

@Service
public class SousSousCategorieDAO {
	@Autowired
	SousSousCategorieRepository sousSousCategorieRepository;
	public SsCategorie save(SsCategorie c) {
		return sousSousCategorieRepository.save(c);
	}

	public List<SsCategorie> findAll() {
		return sousSousCategorieRepository.findAll();
	}

	public SsCategorie findOne(Long id) {
		return sousSousCategorieRepository.getOne(id);
	}

	public void Delete(SsCategorie sc) {
		sousSousCategorieRepository.delete(sc);
	}
}
