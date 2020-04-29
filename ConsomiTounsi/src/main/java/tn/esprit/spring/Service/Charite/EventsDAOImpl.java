package tn.esprit.spring.Service.Charite;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.Charite.ChariteRepository;
import tn.esprit.spring.Repository.Charite.EventsRepository;
import tn.esprit.spring.Repository.Charite.PubRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Service("EventsDAO")
public class EventsDAOImpl implements EventsDAO {
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	PubRepository publiciteRepository;
	@Autowired
	private ChariteRepository chariteRepository;
	@Autowired
    private UserService service;
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

	
	  public static final String ACCOUNT_SID = "AC25eeab7c940f79dd272d5bc2d7337437";
	  public static final String AUTH_TOKEN = "cf00808dd9240106de0943465ae7408e";
	 public void sendSms(){
			 


		    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		    Message message = Message.creator(new PhoneNumber("+21629651973"),
		        new PhoneNumber("+18654261966"), 
		        "I registered for this event").create();

		    System.out.println(message.getSid());
		  }

 //@Scheduled(cron="0 0 0 * * ?")
//@Scheduled(fixedRate = 2000L)
	public void removeOldItems() {

			eventsRepository.removeOlder();


	
	 
	}
	 //@Scheduled(fixedRate = 2000L)
	   public void cronJobSch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("Java cron job expression:: " + strDate);

	 }
	   /* affiche les events de jour */
	@Override
	//@Scheduled(fixedRate = 2000L)
	public List<Events> getEventsParDate() {
		// TODO Auto-generated method stub
		return eventsRepository.findLikeDate();
	}


	
}
