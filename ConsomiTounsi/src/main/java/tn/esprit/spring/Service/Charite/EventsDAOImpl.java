package tn.esprit.spring.Service.Charite;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Repository.Charite.EventsRepository;
import tn.esprit.spring.Repository.Charite.PubRepository;

@Service("EventsDAO")
public class EventsDAOImpl implements EventsDAO {
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	PubRepository publiciteRepository;

	@Override
	public Events saveEvents(Events Events) {
		return eventsRepository.save(Events);
	}

	@Override
	public Events upsateEvents(Events Events) {
		return eventsRepository.saveAndFlush(Events);
	}

	@Override
	public List<Events> getAllEventsList() {

		return eventsRepository.findAll();
	}

	@Override
	public void deleteEventsById(long Id) {
		eventsRepository.deleteById(Id);

	}

	@Override
	public List<Events> findLikeName(String titre) {
		return eventsRepository.findLikeName(titre);

	}
	public Events findOne(Long id) {
		return eventsRepository.getOne(id);
	}

	@Override
	public int saveEvent(Long publicite, Events Events) {
		Pub p = publiciteRepository.findById(publicite).get();
		Events.setPublicite(p);
		eventsRepository.save(Events);
		return Events.getId().intValue();
	}
	/*@Override
	@Scheduled(cron="* * * ? * *")
	public void removeOldItems() {
		 Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, -360);

	        java.sql.Date oneYear = new java.sql.Date(cal.getTimeInMillis());
		eventsRepository.removeOlderThan(oneYear);
	     
	}*/

	

	
}
