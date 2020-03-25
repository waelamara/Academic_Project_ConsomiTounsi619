package tn.esprit.spring.DAO.Charite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.Charite.EndroitRepository;
import tn.esprit.spring.Repository.Charite.EventsRepository;

@Service("EndroitDAO")
public class EndroitDAOImp implements EndroitDAO{
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	private EndroitRepository endroitRepository;

	@Override
	public List<Endroit> getAllEndroitList() {
		// TODO Auto-generated method stub
		return endroitRepository.findAll();
	}

	@Override
	public int saveEndroit(Long ideventss, Endroit Endroit) {
		Events events = eventsRepository.findById(ideventss).get();
		Endroit.setEventss(events);
		endroitRepository.saveAndFlush(Endroit);
		return Endroit.getId().intValue();
	}

	@Override
	public Endroit findOne(Long id) {
		// TODO Auto-generated method stub
		return endroitRepository.getOne(id);
	}

}
