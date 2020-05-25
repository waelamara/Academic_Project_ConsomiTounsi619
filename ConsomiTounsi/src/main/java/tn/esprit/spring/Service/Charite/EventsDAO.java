package tn.esprit.spring.Service.Charite;

import java.util.List;

import org.primefaces.model.file.UploadedFiles;

import tn.esprit.spring.Model.Charite.Events;

public interface EventsDAO {
	Events saveEvents(Events Events);
	public void  saveEventss(Events e,UploadedFiles files);

	Events upsateEvents(Events Events);

	List<Events> getAllEventsList();

	List<Events> findLikeName(String titre);

    Events findOne(Long id);

	void deleteEventsById(long Id);
	public int saveEvent(Long publicite,Events Events);
	public void sendSms();
	public void removeOldItems();
	List<Events> getEventsParDate();
	public String save();

}
