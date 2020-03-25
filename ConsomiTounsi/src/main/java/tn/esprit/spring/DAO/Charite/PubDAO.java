package tn.esprit.spring.DAO.Charite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Repository.Charite.PubRepository;

@Service
public class PubDAO {
	@Autowired
	PubRepository publiciteRepository;

	public Pub save(Pub p) {
		return publiciteRepository.save(p);
	}

	public List<Pub> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Pub c) {
		publiciteRepository.delete(c);
	}

	public Pub findOne(Long id) {
		return publiciteRepository.getOne(id);
	}

}
