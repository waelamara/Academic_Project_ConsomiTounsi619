package tn.esprit.spring.Service.Rayon;

import java.util.List;

import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Rayon.Rayon;

public interface IRayonService {
	Rayon saveRayon(Rayon rayon);

	Rayon updateRayon(Rayon rayon);

	List<Rayon> getAllRayon();

	void deleteRayonById(long Idrayon);
	public List<Rayon> findRayonbyName(String name);
	
	public void affecterProduitARayon(Long Idrayon, Long Idproduit);
	
	public void desaffecterProduitduRayon(Long Idrayon, Long Idproduit);
	
	public List<Produit> findProduitNameParRayon(Long Idrayon);

	public List<Produit> getAllProduitName();
}
