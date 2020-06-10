package tn.esprit.spring.Service.Publicite;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import Utils.AppConstants;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.Publicite.PubliciteRepository;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Service
public class PubliciteServiceImpl implements IPubliciteService {
	@Autowired
	PubliciteRepository publiciteRepository;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;

	ObjectMapper objectMapper = new ObjectMapper();

	public Publicite save(Publicite p) {
		return publiciteRepository.save(p);
	}

	public List<Publicite> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Long id) {
		Publicite p2 = findOne(id);
		publiciteRepository.delete(p2);
	}

	public Publicite findOne(Long id) {
		return publiciteRepository.getOne(id);
	}

	public int NbrUserFemme() {
		return publiciteRepository.CountFemmeFromUser();
	}

	public int NbrUserHomme() {
		return publiciteRepository.CountHommeFromUser();
	}

	public int NbrUserTotal() {
		return publiciteRepository.CountALLUser();
	}

	public int NbrUserAgeBetwin(int ageCibledebut, int ageCibleFin) {
		return publiciteRepository.CountUserWithAgeBetwin(ageCibledebut, ageCibleFin);
	}

	public int NbrUserFemmeAgeBetwin(int ageCibledebut, int ageCibleFin) {
		return publiciteRepository.CountUserFemmeWithAgeBetwin(ageCibledebut, ageCibleFin);
	}

	public int NbrUserHommeAgeBetwin(int ageCibledebut, int ageCibleFin) {
		return publiciteRepository.CountUserHommeWithAgeBetwin(ageCibledebut, ageCibleFin);
	}

	public Publicite Update(Publicite p, Long id) {
		Publicite p2 = findOne(id);
		p2.setCanal(p.getCanal());
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setNbrInitialVueCible(p.getNbrInitialVueCible());
		p2.setNbrFinalVue(p.getNbrFinalVue());
		p2.setCout(p.getCout());
		Publicite PubliciteUpdated = save(p2);
		return PubliciteUpdated;
	}

	public Publicite Add(String ProduitJson, MultipartFile file)
			throws JsonMappingException, JsonProcessingException, IOException, ParseException {
		Publicite pub = objectMapper.readValue(ProduitJson, Publicite.class);
		Publicite PubWithImg = AffecterImageVideoPub(pub, file);
		String typefile = TypeFile(file);

		float coutPub = CalculeCoutTotalPub(pub.getGenderCible().toString(), pub.getCanal().toString(),
				pub.getDebutAgeCible(), pub.getFinAgeCible(), pub.getDateDebut().toString(),
				pub.getDateFin().toString(), typefile);
		PubWithImg.setCout(coutPub);
		PubWithImg.setNbrInitialVueCible(CountUserCible(pub.getDebutAgeCible(),pub.getFinAgeCible(),pub.getGenderCible().toString()));
		return publiciteRepository.save(PubWithImg);

	}
	
	public Publicite AddPub(Publicite pub, UploadedFile file) throws IOException, ParseException {
		Publicite PubWithImg = AffecterImageVideoPubs(pub, file);
		String typefile = TypeFile(file);

		float coutPub = CalculeCoutTotalPub(pub.getGenderCible().toString(), pub.getCanal().toString(),
				pub.getDebutAgeCible(), pub.getFinAgeCible(), pub.getDateDebut().toString(),
				pub.getDateFin().toString(), typefile);
		PubWithImg.setCout(coutPub);
		PubWithImg.setNbrInitialVueCible(CountUserCible(pub.getDebutAgeCible(),pub.getFinAgeCible(),pub.getGenderCible().toString()));
		publiciteRepository.save(PubWithImg);
		
		String token="EAAoc6WpqZBZBEBAMikPzLLiBxJyS5EuYHuJHk5s1ApIdOZAJj9Gl37j6ZCLzU1t86ZAojZBc6VIeePRpJMzINUqUDHTjIhRvTUcFrKhrqLrZCiD36PZBruBIaAznWOL8tkFL9Pi2K4tWqrhSU0fbpK0WoWwxw5h5voSMK3PSO7gkBBxZAs3PGu5836gAgGoU2wZAMZD";
		if(PubWithImg.getCanal().toString().equals("SITE_ET_FACEBOOK")){
			if (typefile.equals("Image")){
				InputStream inputfile = file.getInputStream();
				FacebookClient fb= new DefaultFacebookClient(token);
				FacebookType response=fb.publish("me/photos", FacebookType.class,BinaryAttachment.with(file.getFileName(), inputfile), Parameter.with("message", PubWithImg.getNom()));
				System.out.println(response.getId());
			}
			if (typefile.equals("Video") ){
				InputStream inputfile = file.getInputStream();
				FacebookClient fb= new DefaultFacebookClient(token);
				FacebookType response=fb.publish("me/videos", FacebookType.class,BinaryAttachment.with(file.getFileName(), inputfile), Parameter.with("message", PubWithImg.getNom()));
				System.out.println(response.getId());
			}
		}
		
		return PubWithImg ;

	}
	
	public Publicite UpdatePubWithoutImage(Publicite pub) throws IOException, ParseException {
		String typefile;
		if(pub.getImage()!=null){
			 typefile = "Image";
		}
		else{
			 typefile = "Video";
		}
		float coutPub = CalculeCoutTotalPub(pub.getGenderCible().toString(), pub.getCanal().toString(),
				pub.getDebutAgeCible(), pub.getFinAgeCible(), pub.getDateDebut().toString(),
				pub.getDateFin().toString(), typefile);
		pub.setCout(coutPub);
		pub.setNbrInitialVueCible(CountUserCible(pub.getDebutAgeCible(),pub.getFinAgeCible(),pub.getGenderCible().toString()));
		return publiciteRepository.save(pub);

	}

	public int CountUserCible(int ageCibledebut, int ageCibleFin, String gender) {
		int nbrUser = 0;
		if (gender.equals("HOMME")) {
			nbrUser += NbrUserHommeAgeBetwin(ageCibledebut, ageCibleFin);
		}
		if (gender.equals("FEMME")) {
			nbrUser += NbrUserFemmeAgeBetwin(ageCibledebut, ageCibleFin);
		}
		if (gender.equals("TOUS")) {
			nbrUser += NbrUserAgeBetwin(ageCibledebut, ageCibleFin);
		}

		return nbrUser;
	}

	public float CalculeCoutTotalPub(String gender, String canal, int ageCibledebut, int ageCibleFin, String dateDebut,
			String dateFin, String typePub) throws ParseException {
		float cout = CoutSurCanal(canal);
		if (gender.equals("HOMME") || gender.equals("FEMME")) {
			cout += 200;
		}
		cout += CoutSurLeNbrDeJour(dateDebut, dateFin);
		cout += CoutSurTrancheAge(ageCibledebut, ageCibleFin);
		if (typePub.equals("Video")) {
			cout += 100;
		}
		if (typePub.equals("Image"))
			cout += 50;
		return cout;
	}

	public int CoutSurLeNbrDeJour(String dateDebut, String dateFin) throws ParseException {
		if(dateDebut!=null && dateFin!=null){
		int NbrJourPub = DifferenceJourDateDebutEtDateFin(dateDebut, dateFin);
		int cout = 0;
		if (NbrJourPub <= 30) {
			return cout += 2 * NbrJourPub;
		}
		if (NbrJourPub > 30 && NbrJourPub <= 90) {
			return cout += 3 * NbrJourPub;
		}
		if (NbrJourPub > 90 && NbrJourPub <= 180) {
			return cout += 5 * NbrJourPub;
		} else {
			return cout += 10 * NbrJourPub;
		}
		}
		else return 0;

	}

	public int DifferenceJourDateDebutEtDateFin(String dateDebut, String dateFin) throws ParseException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		LocalDate dateD = LocalDate.parse(dateDebut, format);
		LocalDate dateF = LocalDate.parse(dateFin, format);
		int periodJour;
		int yearDef = dateF.getYear() - dateD.getYear();
		if (yearDef == 0) {
			periodJour = dateF.getDayOfYear() - dateD.getDayOfYear();
			return periodJour;
		} else {
			int nbranne = yearDef * 365;
			periodJour = (dateF.getDayOfYear() - dateD.getDayOfYear()) + nbranne;
			return periodJour;
		}
	}

	public int CoutSurCanal(String canal) {
		if (canal.equals("SITE_ET_FACEBOOK")) {
			return 300;
		}
		if (canal.equals("SITE_ET_TWITTER")) {
			return 150;
		} else
			return 100;
	}

	public int CoutSurTrancheAge(int ageCibledebut, int ageCibleFin) {
		if(ageCibledebut!=0 && ageCibleFin!=0){
			int TrancheAge = ageCibleFin - ageCibledebut;
			int cout = 500;
			for (int i = 1; i <= TrancheAge; i++) {
				cout -= 10;
			}
			return cout;
		}
		else return 0;
	}

	public String TypeFile(UploadedFile file) throws IOException {
		if(file.getSize()==0){
			return "Nofile";
		}
		else{
		String fileName = fileStorageServiceImpl.UploadImages(file);
		int length = fileName.length();
		String typefile = fileName.substring(length - 3, length);
		if (typefile.equals("png") || typefile.equals("peg") || typefile.equals("jpg")) {
			return "Image";
		} 
		else {
			return "Video";
		}
		}
	}
	

	public Publicite AffecterImageVideoPub(Publicite pub, MultipartFile file) throws IOException {
		String fileName = fileStorageServiceImpl.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
				.path(fileName).toUriString();
		String typefile = TypeFile(file);
		if (typefile.equals("Image")) {
			pub.setImage(fileDownloadUri);
		} else {
			pub.setVideo(fileDownloadUri);
		}
		return pub;
	}
	
	public Publicite AffecterImageVideoPubs(Publicite pub, UploadedFile file) throws IOException {
		String fileName = fileStorageServiceImpl.UploadImages(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH)
				.path(fileName).toUriString();
		String typefile = TypeFile(file);
		if (typefile.equals("Image")) {
			pub.setImage(fileDownloadUri);
		} else {
			pub.setVideo(fileDownloadUri);
		}
		return pub;
	}

	
	public String TypeFile(MultipartFile file) throws IOException {
		String fileName = fileStorageServiceImpl.storeFile(file);
		int length = fileName.length();
		String typefile = fileName.substring(length - 3, length);
		if (typefile.equals("png") || typefile.equals("peg") || typefile.equals("jpg")) {
			return "Image";
		} else {
			return "Video";
		}
	}

	@Override
	public List<Publicite> findLikeName(String nom) {
		return publiciteRepository.findLikeName(nom);
	}

	@Override
	public List<Publicite> findByCanal(String canal) {
		return publiciteRepository.findByCanal(canal);
	}
	
	public Date ConvertirDate(String date) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf1.parse(date));
		calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
	}
	
	public List<Publicite> findPubForAll(){
		return publiciteRepository.findPubForAll();
	}

	@Override
	public List<Publicite> findByCanalAndName(String canal, String nom) {
		return publiciteRepository.findByCanalAndName(canal, nom);
	}

	@Override
	public List<Publicite> getPubForUserConnecter(Date UserDateNaissance, String gender) {
		return publiciteRepository.getPubForUserConnecter(UserDateNaissance, gender);
	}

}
