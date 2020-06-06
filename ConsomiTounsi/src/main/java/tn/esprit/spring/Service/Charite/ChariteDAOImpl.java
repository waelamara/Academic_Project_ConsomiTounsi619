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
import org.springframework.transaction.annotation.Transactional;

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
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
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
import tn.esprit.spring.Service.Panier.CommandeImpl;

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
	@Autowired
	CommandeImpl commandeDao;
	

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
	public List<Charite> getChariteC(Long iduser) {
		List<Charite> charite=new ArrayList<>();
		charite= chariteRepository.getChariteC(iduser);
		return charite;
		
	}
	@Override
	public List<Charite> getChariteM(Long iduser) {
		List<Charite> charite=new ArrayList<>();
		charite= chariteRepository.getChariteM(iduser);
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

	
@Override
public boolean saveCharit2(long idevents,Charite Charite) {
	// TODO Auto-generated method stub
	Events e1 = eventDAO.findOne(idevents);
	Charite.setIdevents(e1);
	chariteRepository.save(Charite);
	return true;
}
@Override
public boolean saveCharitCom(Long idcommande,Charite Charite) {
	// TODO Auto-generated method stub
	Commande c1= commandeDao.findOne(idcommande);
	Set<Commande> c= new HashSet<Commande>();
	c.add(c1);
	//Events e1 = eventDAO.findOne(idcommande);
	Charite.setCommandeCharite(c);
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


public int saveCharite5(Long idevents,Charite Charite) {
	Events events = eventsRepository.findById(idevents).get();
	//User user= userRepository.findById(iduser).get();
	Charite.setIdevents(events);
	
//	Charite.setIduser(user);
	chariteRepository.save(Charite);
	return Charite.getId().intValue();
	//return chariteRepository.save(Charite);

}

@Override
public void deleteChariteById(long Id) {
	chariteRepository.deleteById(Id);
	
}

@Override
public List<Charite> getAllCharCom(Long id) {
	// TODO Auto-generated method stub
	return chariteRepository.getChariteCommande1(id);
}

@Transactional
public void Pay(Long idchar, String carta, int expMonth, int expYear, String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
	Charite  c= chariteDAO.findOne(idchar);
	
		
			Map<String, Object> params = new HashMap<>();
	        Map<String, Object> tokenParams = new HashMap<>();
	        Map<String, Object> cardParams = new HashMap<>();
	        Stripe.apiKey = "sk_test_hdyMhjLdHTOXLus1N6lpzlVR00QHGJM1Na";
	        cardParams.put("number", carta);
	        cardParams.put("exp_month", expMonth);
	        cardParams.put("exp_year", expYear);
	        cardParams.put("cvc", cvc);
	        int nMontant= (int) (c.getMontantPaye()*100);
	        tokenParams.put("card", cardParams);
	        Token token =Token.create(tokenParams);
	      //  System.out.println(token.getCard().getId());
	        if (token.getId()!=null){
	        params.put("amount", nMontant);
	        params.put("description", "test de stipe");
	        params.put("currency", "eur");
	        params.put("source", token.getId());
	        Charge charge = Charge.create(params);
	        
	        }
}

}