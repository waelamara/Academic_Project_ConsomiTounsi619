package tn.esprit.spring.Service.Livraison;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Repository.LivraisonRepository;
import tn.esprit.spring.Service.Livreur.LivreurService;

@Service
public class LivraisonService {
	@Autowired
	LivraisonRepository LivraisonRepository;

	private static final Logger L = LogManager.getLogger(LivreurService.class);

	/* Ajouter un livraison */
	public Livraison save(Livraison liv) {
		return LivraisonRepository.save(liv);

	}

	/* voir tous les livraisons */
	public List<Livraison> findall() {
		List<Livraison> a = LivraisonRepository.findAll();

		for (Livraison Livraison : a) {
			L.info("Livraison :" + Livraison);

		}
		return a;
	}

	/* Delete D"un Livraison */
	public void delete(long id) {
		LivraisonRepository.deleteById(id);
	}

	/* Update d'un Livraison */
	public Livraison updateLiv(Livraison Liv) {
		return LivraisonRepository.save(Liv);

	}

	/* Chercher un livraison */
	public Livraison findOne(long liv) {
		return LivraisonRepository.getOne(liv);
	}

	
}
