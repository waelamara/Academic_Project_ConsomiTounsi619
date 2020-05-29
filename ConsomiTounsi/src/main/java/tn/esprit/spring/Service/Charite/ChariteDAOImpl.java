package tn.esprit.spring.Service.Charite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;

import tn.esprit.spring.Model.ChargeRequest;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Charite.Charite;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Charite.ChariteRepository;
import tn.esprit.spring.Repository.Charite.EventsRepository;

@Service("ChariteDAO")
public class ChariteDAOImpl implements ChariteDAO {
	@Autowired
	private ChariteRepository chariteRepository;
	@Autowired
	private EventsRepository eventsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	ChariteDAO chariteDAO;
	@Autowired
	EventsDAO eventDAO;
	

	@Override
	public boolean saveCharit(Charite Charite) {
		// TODO Auto-generated method stub
		chariteRepository.save(Charite);
		return true;
	}

	@Override
	public List<Charite> getAllChariteList() {
		return chariteRepository.findAll();

	}

	@Override
	public int saveCharite(Long idevents,Long iduser,Charite Charite) {
		Events events = eventsRepository.findById(idevents).get();
		User user= userRepository.findById(iduser).get();
		Charite.setIdevents(events);
		Charite.setIduser(user);
		chariteRepository.save(Charite);
		return Charite.getId().intValue();
		//return chariteRepository.save(Charite);

	}

	@Override
	public Charite findOne(Long id) {
		return chariteRepository.getOne(id);

	}

	@Override
	public int saveCharite1(Long idevents,Long iduser,Charite Charite) {
		Events events = eventsRepository.findById(idevents).get();
		User user= userRepository.findById(iduser).get();
		Charite.setIdevents(events);
		Charite.setIduser(user);
		chariteRepository.save(Charite);
		return Charite.getId().intValue();
		//return chariteRepository.save(Charite);

	}

	

	@Override
	public List<Commande> getCommande(Long idCommande) {
		Commande c= commandeRepository.findById(idCommande).get();
		List<Commande> Commande=new ArrayList<>();
		for(Commande com : Commande)
			Commande.add(com);
		return Commande;
	}

	@Override
	public int saveCharitee(Long idevents, Long iduser, Long idcommande, Charite Charite) {
		Events events = eventsRepository.findById(idevents).get();
		User user= userRepository.findById(iduser).get();
		Commande c1=commandeRepository.findById(idcommande).get();
		Set<Commande> c= new HashSet<Commande>();
		c.add(c1);
		//c1.stream().collect(Collectors.toSet()); 
		Charite.setIdevents(events);
		Charite.setIduser(user);
		Charite.setCommandeCharite(c);
		chariteRepository.save(Charite);
		return Charite.getId().intValue();
	}

	@Override
	public List<Charite> getCharite(Long iduser) {
		List<Charite> charite=new ArrayList<>();
		charite= chariteRepository.getCharite(iduser);
		return charite;
		
	}

	@Override
	public Charite findOnes(Long id) {
		return chariteRepository.getChariteUser(id);
	}

	

	public void facturepdf (int id_charite){
		
	}
	
	@Value("${stripe.keys.secret}")
	private String secretKey;

	@PostConstruct
	public void init() {
		Stripe.apiKey = secretKey;
	}
public String createStripeCustomer(int idUser) {
		
		// stripe key
		Stripe.apiKey = "sk_test_hdyMhjLdHTOXLus1N6lpzlVR00QHGJM1Na";

		User user = userRepository.findById((long) idUser).get();
		Map<String, Object> params = new HashMap<>();
		params.put("description", "My First Test Customer (created for API docs)");
		params.put("email", user.getEmail());

		// affichage id du customer
		try {
			Customer customer = Customer.create(params);

			System.out.println("create customer id: {}");
			return customer.getId();
		} catch (StripeException e) {

			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
//		return null;
	}
public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc)
		throws StripeException {
	// TODO Auto-generated method stub
	//return null;
	// stripe key
	Stripe.apiKey = "sk_test_hdyMhjLdHTOXLus1N6lpzlVR00QHGJM1Na";

	Customer customer = Customer.retrieve(customerId);

	Map<String, Object> cardParam = new HashMap<String, Object>();
	cardParam.put("number", carta);
	cardParam.put("exp_month", expMonth);
	cardParam.put("exp_year", expYear);
	cardParam.put("cvc", cvc);

	Map<String, Object> tokenParam = new HashMap<String, Object>();
	tokenParam.put("card", cardParam);

	Token token = Token.create(tokenParam);

	Map<String, Object> source = new HashMap<String, Object>();
	source.put("source", token.getId());

	customer.getSources().create(source);
	return token.getId();
}
public String paymentIntent(ChargeRequest chargerequest)throws StripeException{
	// TODO Auto-generated method stub
	//return null;
	// stripe key
	Stripe.apiKey = "sk_test_hdyMhjLdHTOXLus1N6lpzlVR00QHGJM1Na";

	// `source` is obtained with Stripe.js; see
	// https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
	List<String> paymentMethodTypes = new ArrayList();
	paymentMethodTypes.add("card");
	
	
	Map<String, Object> params = new HashMap<>();
	params.put("amount",chargerequest.getAmount());
	params.put("currency", chargerequest.getCurrency());
	params.put("description", chargerequest.getDescription());
	params.put("payment_method_types", paymentMethodTypes);
	
	PaymentIntent p = PaymentIntent.create(params);
	p.getId();
	return p.getId();
}
public PaymentIntent confirm(Charite Charite,String id,Long idcharite,int iduser) throws StripeException {
	Stripe.apiKey = "sk_test_hdyMhjLdHTOXLus1N6lpzlVR00QHGJM1Na";
	PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
	Map<String, Object> params = new HashMap<>();
	params.put("payment_method", "pm_card_visa");
	// params.put("customer", "cus_H1OvsnwEn1KX36");
	Charite c =chariteRepository.getOne(idcharite);
	if(c.getIduser().getId()==iduser)
	{
	paymentIntent.confirm(params);
	chariteDAO.saveCharit(Charite);
	
	return paymentIntent;
	}
	return null;
	
}

@Override
public boolean saveCharit2(long idevents,Charite Charite) {
	// TODO Auto-generated method stub
	Events e1 = eventDAO.findOne(idevents);
	Charite.setIdevents(e1);
	chariteRepository.save(Charite);
	return true;
}

@Override
public boolean saveCharit3(Events e1,Charite Charite) {
	// TODO Auto-generated method stub
	
	
	Events e2 = eventDAO.findOne(e1.getId());
	Charite.setIdevents(e2);
	chariteRepository.save(Charite);
	return true;
}

	
}
