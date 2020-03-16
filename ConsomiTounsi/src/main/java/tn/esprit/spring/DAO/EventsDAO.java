package tn.esprit.spring.DAO;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Events;
import tn.esprit.spring.Model.Produit;
import tn.esprit.spring.Repository.EventsRepository;

@Service
public class EventsDAO {
	@Autowired
	EventsRepository eventsRepository;
	public Events save(Events e) {
		return eventsRepository.save(e);
	}

	public List<Events> findAll() {
		return eventsRepository.findAll();
	}

	public void Delete(Events e) {
		eventsRepository.delete(e);
	}

	public Events findOne(Long id) {
		return eventsRepository.getOne(id);
	}
	public List<Events> findLikeName(String titre) {
		return eventsRepository.findLikeName(titre);

	}


}
