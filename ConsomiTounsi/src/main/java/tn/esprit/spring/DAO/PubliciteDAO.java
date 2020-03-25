package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.PubliciteRepository;

@Service
public class PubliciteDAO {
	@Autowired
	PubliciteRepository publiciteRepository;

	public Publicite save(Publicite p) {
		return publiciteRepository.save(p);
	}

	public List<Publicite> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Publicite c) {
		publiciteRepository.delete(c);
	}

	public Publicite findOne(Long id) {
		return publiciteRepository.getOne(id);
	}
}
