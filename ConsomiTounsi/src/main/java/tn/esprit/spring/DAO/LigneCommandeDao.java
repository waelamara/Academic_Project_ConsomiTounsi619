package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Repository.LigneCommandeRepository;


@Service
public class LigneCommandeDao {
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	public List<lignecommandeproduit> panierParIdclient( long id) {
		return ligneCommandeRepository.panierParIdclient(id);

	}
	public LigneCommande findOne(Long id) {
		return ligneCommandeRepository.getOne(id);
	}
	public List<LigneCommande> findAll() {
		return ligneCommandeRepository.findAll();
	}
	

}
