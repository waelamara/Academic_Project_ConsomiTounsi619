package tn.esprit.spring.Service.Charite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

	
}
