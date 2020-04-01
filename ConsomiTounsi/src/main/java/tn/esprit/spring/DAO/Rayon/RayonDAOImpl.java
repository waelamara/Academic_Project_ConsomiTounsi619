package tn.esprit.spring.DAO.Rayon;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Repository.ProduitRepository;
import tn.esprit.spring.Repository.Rayon.RayonRepository;

@Service("RayonDAO")
public class RayonDAOImpl implements RayonDAO {

	@Autowired
	RayonRepository rayonRepository;
	@Autowired
	ProduitRepository produitRepository;

	@Override
	public Rayon saveRayon(Rayon rayon) {
		return rayonRepository.save(rayon);
	}

	@Override
	public Rayon updateRayon(Rayon rayon) {
		return rayonRepository.saveAndFlush(rayon);

	}

	@Override
	public List<Rayon> getAllRayon() {

		return rayonRepository.findAll();
	}

	@Override
	public void deleteRayonById(long Idrayon) {
		rayonRepository.deleteById(Idrayon);

	}

	@Override
	public List<Rayon> findRayonbyName(String name) {
		return rayonRepository.findRayonbyName(name);
	}

	public List<Rayon> findRayonbyName1(String name) {
		return rayonRepository.findRayonbyName(name);
	}
	
	@Override
	public void affecterProduitARayon(long Idrayon, long Idproduit) {
		Rayon rayon = rayonRepository.findById(Idrayon).get();
		Produit produit = produitRepository.findById(Idproduit).get();
		
		
		produit.setIdrayon(rayon);
		produitRepository.save(produit);

		
	}

}
