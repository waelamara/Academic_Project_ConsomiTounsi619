package tn.esprit.spring.Service.Charite;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.Charite.EndroitRepository;
import tn.esprit.spring.Repository.Charite.EventsRepository;
import tn.esprit.spring.Service.Panier.CommandeImpl;

@Service("EndroitDAO")
public class EndroitDAOImp implements EndroitDAO{
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	private EndroitRepository endroitRepository;
	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	private Endroit en = new Endroit();
	

	public Endroit getEn() {
		return en;
	}

	public void setEn(Endroit en) {
		this.en = en;
	}

	@Override
	public List<Endroit> getAllEndroitList() {
		// TODO Auto-generated method stub
		return endroitRepository.findAll();
	}

	@Override
	public int saveEndroit1(Events e, Endroit Endroit) {
	//	Events events = eventsRepository.findById(ideventss).get();
	//	Endroit.setEventss(events);
		endroitRepository.saveAndFlush(Endroit);
		return Endroit.getId().intValue();
	}
	@Override
	public int saveEndroit(Long ideventss, Endroit Endroit) {
	Events events = eventsRepository.findById(ideventss).get();
	//Endroit en1=endroitRepository.findById(en.getId()).get();
	Endroit.setEventss(events);
		endroitRepository.saveAndFlush(Endroit);
		return Endroit.getId().intValue();
	}
	@Override
	public Endroit findOne(Long id) {
		// TODO Auto-generated method stub
		return endroitRepository.getOne(id);
	}

	@Override
	public Endroit saveEndroit1(Endroit Endroit) {
		return endroitRepository.save(Endroit);

	}
	@Override
	public int saveEndroit(Long ideventss,Long idendroit, Endroit e) {
	//Events events = eventsRepository.findById(ideventss).get();
		Events ev = eventDAO.findOne(ideventss);
	Endroit en1 = findOne(idendroit);
	//e.setNbplace(en1.getNbplace());
	//e.setEmplacement(en1.getEmplacement());
	//e.setStatu("reserve");
	//Endroit en1=endroitRepository.findById(en.getId()).get();
	e.setEventss(ev);
		endroitRepository.saveAndFlush(e);
		return e.getId().intValue();
	}
	@Override
	public int saveEndroit2(Long idendroit, Endroit e) {
	Endroit en1 = findOne(idendroit);
	
		endroitRepository.saveAndFlush(e);
		return e.getId().intValue();
	}

	

	@Override
	public List<Endroit> getAllEndroitEv(Long id) {
		// TODO Auto-generated method stub
		return endroitRepository.ListeEndroitE(id);
	}

	@Override
	public List<Endroit> getAllEndroitDi() {
		// TODO Auto-generated method stub
		return endroitRepository.ListeEndroit();
	}

	@Override
	public List<Endroit> getAllEndroitR() {
		// TODO Auto-generated method stub
		return endroitRepository.ListeEndroitR();
	}

	
	

}
