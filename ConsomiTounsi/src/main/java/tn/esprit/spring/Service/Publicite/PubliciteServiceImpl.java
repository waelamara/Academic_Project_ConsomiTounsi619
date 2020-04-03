package tn.esprit.spring.Service.Publicite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.PubliciteRepository;

@Service
public class PubliciteServiceImpl implements IPubliciteService {
	@Autowired
	PubliciteRepository publiciteRepository;

	public Publicite save(Publicite p) {
		return publiciteRepository.save(p);
	}

	public List<Publicite> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Long id) {
		Publicite p2 = findOne(id);
		publiciteRepository.delete(p2);
	}

	public Publicite findOne(Long id) {
		return publiciteRepository.getOne(id);
	}

	public Publicite Update(Publicite p, Long id) {
		Publicite p2 = findOne(id);
		p2.setCanal(p.getCanal());
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setNbrInitialVueCible(p.getNbrInitialVueCible());
		p2.setNbrFinalVue(p.getNbrFinalVue());
		p2.setCout(p.getCout());
		Publicite PubliciteUpdated = save(p2);
		return PubliciteUpdated;
	}
}
