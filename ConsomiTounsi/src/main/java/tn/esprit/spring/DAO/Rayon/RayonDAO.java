package tn.esprit.spring.DAO.Rayon;

import java.util.List;


import tn.esprit.spring.Model.Rayon.Rayon;

public interface RayonDAO {
	Rayon saveRayon(Rayon rayon);

	Rayon updateRayon(Rayon rayon);

	List<Rayon> getAllRayon();

	void deleteRayonById(Long Idrayon);
	
	public List<Rayon> findRayonbyName(String name);
	
	public void affecterProduitARayon(Long Idrayon, Long Idproduit);


}
