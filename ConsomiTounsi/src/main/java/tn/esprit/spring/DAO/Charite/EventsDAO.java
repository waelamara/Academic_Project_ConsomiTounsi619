package tn.esprit.spring.DAO.Charite;

import java.util.List;

import tn.esprit.spring.Model.Charite.Events;

public interface EventsDAO {
	Events saveEvents(Events Events);

	Events upsateEvents(Events Events);

	List<Events> getAllEventsList();

	List<Events> findLikeName(String titre);

    Events findOne(Long id);

	void deleteEventsById(long Id);
	public int saveEvent(Long publicite,Events Events);

}
