package tn.esprit.spring.DAO.Charite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Charite.ChariteRepository;
import tn.esprit.spring.Repository.Charite.EventsRepository;

@Service("ChariteDAO")
public class ChariteDAOImpl implements ChariteDAO {
	@Autowired
	private ChariteRepository chariteRepository;
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public boolean saveCharit(Charite Charite) {
		// TODO Auto-generated method stub
		chariteRepository.save(Charite);
		return true;
	}

	@Override
	public List<Charite> getAllChariteList() {
		return chariteRepository.findAll();

	}

	@Override
	public int saveCharite(Long idevents,Long iduser,Charite Charite) {
		Events events = eventsRepository.findById(idevents).get();
		User user= userRepository.findById(iduser).get();
		Charite.setIdevents(events);
		Charite.setIduser(user);
		chariteRepository.save(Charite);
		return Charite.getId().intValue();
		//return chariteRepository.save(Charite);

	}

	@Override
	public Charite findOne(Long id) {
		return chariteRepository.getOne(id);

	}

	@Override
	public int saveCharite1(Long idevents,Long iduser,Charite Charite) {
		Events events = eventsRepository.findById(idevents).get();
		User user= userRepository.findById(iduser).get();
		Charite.setIdevents(events);
		Charite.setIduser(user);
		chariteRepository.save(Charite);
		return Charite.getId().intValue();
		//return chariteRepository.save(Charite);

	}
}
