package tn.esprit.spring.Service.Charite;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Repository.Charite.EventsRepository;
import tn.esprit.spring.Repository.Charite.PubRepository;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Service("EventsDAO")
public class EventsDAOImpl implements EventsDAO {
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	PubRepository publiciteRepository;
	private JavaMailSender javaMailSender;

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

	@Override
	public void sendSms() {
		// TODO Auto-generated method stub
		
	}
	/*public class SmsSender {
	    // Find your Account Sid and Auth Token at twilio.com/console
	    public static final String ACCOUNT_SID =
	            "AC25eeab7c940f79dd272d5bc2d7337437";
	    public static final String AUTH_TOKEN =
	            "your_auth_token";

	    public static void main(String[] args) {
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message
	                .creator(new PhoneNumber("+14159352345"), // to
	                        new PhoneNumber("+14158141829"), // from
	                        "Where's Wallace?")
	                .create();

	        System.out.println(message.getSid());
	    }
	}*/

	


	

	
}
