package tn.esprit.spring.DAO;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Events;
import tn.esprit.spring.Repository.EventsRepository;

@Service("EventsDAO")
public class EventsDAOImpl implements EventsDAO {
	@Autowired
	private EventsRepository eventsRepository;

	private static final Logger l = LogManager.getLogger(EventsDAOImpl.class);

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
		List<Events> events = (List<Events>) eventsRepository.findAll();
		for (Events event : events) {
			l.info("event ++ :" + event);
		}
		return events;
	}

	@Override
	public Optional<Events> findEventsById(long Id) {
		return eventsRepository.findById(Id);

	}

	@Override
	public void deleteEventsById(long Id) {
		eventsRepository.deleteById(Id);

	}

}
