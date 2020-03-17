package tn.esprit.spring.DAO;

import java.util.Optional;
import java.util.List;

import tn.esprit.spring.Model.Events;

public interface EventsDAO {
	Events saveEvents(Events Events);

	Events upsateEvents(Events Events);

	List<Events> getAllEventsList();

	Optional<Events> findEventsById(long Id);

	void deleteEventsById(long Id);
}
