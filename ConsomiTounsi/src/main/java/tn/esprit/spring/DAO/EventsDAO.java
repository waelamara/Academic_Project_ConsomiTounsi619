package tn.esprit.spring.DAO;

import java.util.List;

import tn.esprit.spring.Model.Events;

public interface EventsDAO {
	Events saveEvents(Events Events);

	Events upsateEvents(Events Events);

	List<Events> getAllEventsList();

	List<Events> findLikeName(String titre);

	void deleteEventsById(long Id);
}
