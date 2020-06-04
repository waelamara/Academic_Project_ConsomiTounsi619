package tn.esprit.spring.Controller.Charite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;

import tn.esprit.spring.Controller.GestionUser.LoginController;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.Charite.ChariteRepository;
import tn.esprit.spring.Service.Charite.ChariteDAO;
import tn.esprit.spring.Service.Charite.EndroitDAO;
import tn.esprit.spring.Service.Charite.EventsDAO;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.security.services.UserDetailsImpl;

@Controller(value = "ControllerCharite")
@ELBeanName(value = "ControllerCharite")
@Join(path = "/addcharite", to = "/AddCharite.jsf")
public class ControllerCharite {
	private String typeCharite;
	private float montantPaye;
	private User iduser;
	private Set<Commande> commandeCharite = new HashSet<>();
	private Events idevents;
	public static Long ide;
	public static Long idc;
	private String carta;

	private int expMonth;

	private int expYear;

	private String cvc;

	// private static List<Long> ch ;

	public String getCarta() {
		return carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}

	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Set<Commande> getCommandeCharite() {
		return commandeCharite;
	}

	public void setCommandeCharite(Set<Commande> commandeCharite) {
		this.commandeCharite = commandeCharite;
	}

	public User getIduser() {
		return iduser;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public Events getIdevents() {
		return idevents;
	}

	public void setIdevents(Events idevents) {
		this.idevents = idevents;
	}

	@Autowired
	EventsDAO eventDAO;
	@Autowired
	CommandeImpl commandeDao;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EndroitDAO endroitDAO;
	@Autowired
	UserService userDAO;
	@Autowired
	private ChariteRepository chariteRepository;

	public String getTypeCharite() {
		return typeCharite;
	}

	public void setTypeCharite(String typeCharite) {
		this.typeCharite = typeCharite;
	}

	public float getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(float montantPaye) {
		this.montantPaye = montantPaye;
	}

	public List<Charite> getAllCharite() {
		return chariteDAO.getAllChariteList();
	}

	@Transactional
	public List<Charite> getAllChariteUser() {

		LoginController.userDetails.getFirstName();
		List<Charite> com = new ArrayList<>();
		// UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getCharite(LoginController.userDetails.getId());
		return com;

	}

	@Transactional
	public List<Charite> getAllChariteUserM() {

		LoginController.userDetails.getFirstName();
		List<Charite> com = new ArrayList<>();
		// UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getChariteM(LoginController.userDetails.getId());
		return com;

	}

	@Transactional
	public List<Charite> getAllChariteUserC() {

		LoginController.userDetails.getFirstName();
		List<Charite> com = new ArrayList<>();
		// UserDetailsImpl u1 = (UserDetailsImpl) authentication.getPrincipal();
		com = chariteDAO.getChariteC(LoginController.userDetails.getId());
		return com;

	}

	@Transactional
	public List<Charite> getAllChariteCo(Long id) {
		return chariteDAO.getAllCharCom(id);
	}

	@Transactional
	public List<Charite> getAllChariteCommande() {
		return chariteRepository.ListeChariteCommande();
	}

	@Transactional
	public List<Charite> getAllChariteMoney() {
		return chariteRepository.ListeChariteMoney();
	}

	@Transactional
	public List<Charite> getChariteCommande(Long id) {
		List<Long> adis = chariteRepository.getChariteCommande(id);
		System.out.println(adis);
		List<Charite> charite = new ArrayList<>();
		for (Long a : adis) {
			Charite c = chariteDAO.findOne(id);
			charite.add(c);
		}
		System.out.println(charite);
		return charite;

	}

	@Transactional
	public List<Charite> getChariteCommande2(Long id) {
		Long adis = chariteRepository.getChariteCommande3(id);
		System.out.println(adis);
		List<Charite> charite = new ArrayList<>();
		// for(Long a : adis){
		Charite c = chariteDAO.findOne(id);
		charite.add(c);
		Commande c1 = commandeDao.findOne(adis);
		// }
		System.out.println(charite);
		return charite;

	}

	/********** Commande *******************/
	@Transactional
	public String addChariteesCommande(Long idevents) {
		Events e1 = eventDAO.findOne(idevents);
		Charite ch = new Charite();
		User u2 = userDAO.findOne(LoginController.userDetails.getId());
		int nb = e1.getNbplace();
		int nbP = e1.getNbparticipant();
		if ((e1.getNbplace() > 0)) {
			Commande c1 = commandeDao.findOne(idc);
			Set<Commande> c = new HashSet<Commande>();
			c.add(c1);
			e1.setTitre(e1.getTitre());
			e1.setDateE(e1.getDateE());
			e1.setEndroit(e1.getEndroit());
			e1.setNbplace(nb - 1);
			e1.setNbparticipant(nbP + 1);
			e1.setPublicite(e1.getPublicite());
			e1.setCharite(e1.getCharite());
			e1.setDescription(e1.getDescription());
			e1.setImage(e1.getImage());
			// Charite.setCommandeCharite(c);
			eventDAO.saveEvents(e1);
			ch.setIdevents(e1);
			ch.setIduser(u2);
			ch.setCommandeCharite(c);
			// ch.setMontantPaye(montantPaye);
			ch.setTypeCharite("dons");
			// userDAO.save(u2);
			// commandeDao.save(c1);
			chariteDAO.saveCharitee(e1.getId(), u2.getId(), c1.getId(), ch);
			// eventDAO.sendSms();

			/*
			  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			  
			  Message message = Message.creator(new
			  PhoneNumber("+21629651973"), new PhoneNumber("+18654261966"),
			  u1.getFirstName()+" "+u1.getLastName()+" "
			  +"I registered for this event "+" "
			  +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
			  +" , "+"donate ordered ID="+" "+c1.getId()+"       "
			  +"thank you so much").create();
			  
			  System.out.println(message.getSid());
			 */

			return "/CharityUser.xhtml?faces-redirect=true";

		}

		else {
			return "insufficient space";

		}
	}

	/********************* charite *******************************/

	public String addCh1(Long idevents) {

		ide = idevents;

		chariteDAO.saveCharit2(idevents, new Charite());
		System.out.println(idevents);
		return "/AddCharite.xhtml?faces-redirect=true";
	}

	public String addChCom(Long idcommande) {

		idc = idcommande;
		// chariteDAO.saveCharitCom(idcommande, new Charite());
		System.out.println("id commande =" + idc);

		return "/EventCharite.xhtml?faces-redirect=true";
	}

	/********* delete ******************/
	public void delete(long Id) {

		chariteDAO.deleteChariteById(Id);

	}

	/*** paiement */
	// http://localhost:8081/event/pay/4/4242424242424242/11/2026/123
	public String Pay(Long idchar, String carta1, int expMonth1, int expYear1, String cvc1)
			throws AuthenticationException, InvalidRequestException, CardException, StripeException {
		String navigateTo = "Charity.xhtml?faces-redirect=true";
		chariteDAO.Pay(idchar, carta, expMonth, expYear, cvc);
		return navigateTo;

	}

	@Transactional
	public String addChariteesMoney2(String carta1, int expMonth1, int expYear1, String cvc1)
			throws AuthenticationException, InvalidRequestException, CardException, StripeException {
		Events e1 = eventDAO.findOne(ide);
		User u2 = userDAO.findOne(LoginController.userDetails.getId());
		Charite ch = new Charite();
		int nb = e1.getNbplace();
		int nbP = e1.getNbparticipant();
		if ((e1.getNbplace() > 0)) {
			e1.setTitre(e1.getTitre());
			e1.setDateE(e1.getDateE());
			e1.setEndroit(e1.getEndroit());
			e1.setNbplace(nb - 1);
			e1.setNbparticipant(nbP + 1);
			e1.setPublicite(e1.getPublicite());
			e1.setCharite(e1.getCharite());
			e1.setDescription(e1.getDescription());
			e1.setImage(e1.getImage());
			eventDAO.saveEvents(e1);
			ch.setIdevents(e1);
			ch.setIduser(u2);
			ch.setMontantPaye(montantPaye);
			ch.setTypeCharite("cagnotte");
			// userDAO.save(u2);
			chariteDAO.saveCharite5(ide, ch);
			chariteDAO.Pay(ch.getId(), carta, expMonth, expYear, cvc);
			/*
			 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			  
			  Message message = Message.creator(new
			  PhoneNumber("+21629651973"), new PhoneNumber("+18654261966"),
			  u1.getFirstName()+" "+u1.getLastName()+" "
			  +"I registered for this event "+" "
			  +e1.getTitre()+" ,"+"the date"+" "+e1.getDateE()
			  +" , "+"donate money"+" "+Charite.getMontantPaye()+"       "
			  +"thank you so much").create();
			  
			  System.out.println(message.getSid());*/
			
			// eventDAO.sendSms(); 

			return "/CharityUser.xhtml?faces-redirect=true";

		}

		else {
			return "/AddCharite.xhtml?faces-redirect=true";

		}
	}

}
