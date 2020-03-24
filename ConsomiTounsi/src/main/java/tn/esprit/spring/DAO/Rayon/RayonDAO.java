package tn.esprit.spring.DAO.Rayon;

import java.util.List;


import tn.esprit.spring.Model.Rayon.Rayon;

public interface RayonDAO {
	Rayon saveRayon(Rayon rayon);

	Rayon updateRayon(Rayon rayon);

	List<Rayon> getAllRayon();

	void deleteRayonById(long Idrayon);

	public List<Rayon> findRayonbyName(String nom_rayon);
}
