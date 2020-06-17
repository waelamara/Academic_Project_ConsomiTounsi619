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

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
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
	
	private JavaMailSender javaMailSender;
	@Autowired
	public  PubliciteServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
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
		PubWithImg.setStatus(true);
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
	public Publicite UpdatePubWithImage(Publicite pub, UploadedFile file) throws IOException, ParseException {
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
			cout += 250;
		}
		cout += CoutSurLeNbrDeJour(dateDebut, dateFin);
		cout += CoutSurTrancheAge(ageCibledebut, ageCibleFin);
		if (typePub.equals("Video")) {
			cout += 250;
		}
		if (typePub.equals("Image"))
			cout += 150;
		return cout;
	}

	public int CoutSurLeNbrDeJour(String dateDebut, String dateFin) throws ParseException {
		if(dateDebut!=null && dateFin!=null){
		int NbrJourPub = DifferenceJourDateDebutEtDateFin(dateDebut, dateFin);
		int cout = 0;
		if (NbrJourPub <= 30) {
			return cout += 5 * NbrJourPub;
		}
		if (NbrJourPub > 30 && NbrJourPub <= 90) {
			return cout += 8 * NbrJourPub;
		}
		if (NbrJourPub > 90 && NbrJourPub <= 180) {
			return cout += 12 * NbrJourPub;
		} else {
			return cout += 20 * NbrJourPub;
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
	

		
			
		


	//@Scheduled(cron = "1 0 0 * * ?")
	//@Scheduled(fixedRate = 5000L)
	public void PublicityFinished() throws MessagingException{
		List<Publicite> listPub=publiciteRepository.findPubFinished();
		for(Publicite p:listPub){
			p.setStatus(false);
			publiciteRepository.save(p);
			float cpm=(p.getCout()/p.getNbrFinalVue())*1000;
			StringBuilder buf = new StringBuilder();
			buf.append("\"<html>\n" + "  <head>\n" + "    <meta name=\"viewport\" content=\"width=device-width\" />\n"
					+ "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
					+ "    <title>Simple Transactional Email</title>\n" + "    <style>\n"
					+ "      /* -------------------------------------\n" + "          GLOBAL RESETS\n"
					+ "      ------------------------------------- */\n" + "      \n"
					+ "      /*All the styling goes here*/\n" + "      \n" + "      img {\n" + "        border: none;\n"
					+ "        -ms-interpolation-mode: bicubic;\n" + "        max-width: 100%; \n" + "      }\n" + "\n"
					+ "      body {\n" + "        background-color: #f6f6f6;\n" + "        font-family: sans-serif;\n"
					+ "        -webkit-font-smoothing: antialiased;\n" + "        font-size: 14px;\n"
					+ "        line-height: 1.4;\n" + "        margin: 0;\n" + "        padding: 0;\n"
					+ "        -ms-text-size-adjust: 100%;\n" + "        -webkit-text-size-adjust: 100%; \n" + "      }\n"
					+ "\n" + "      table {\n" + "        border-collapse: separate;\n" + "        mso-table-lspace: 0pt;\n"
					+ "        mso-table-rspace: 0pt;\n" + "        width: 100%; }\n" + "        table td {\n"
					+ "          font-family: sans-serif;\n" + "          font-size: 14px;\n"
					+ "          vertical-align: top; \n" + "      }\n" + "\n"
					+ "      /* -------------------------------------\n" + "          BODY & CONTAINER\n"
					+ "      ------------------------------------- */\n" + "\n" + "      .body {\n"
					+ "        background-color: #f6f6f6;\n" + "        width: 100%; \n" + "      }\n" + "\n"
					+ "      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\n"
					+ "      .container {\n" + "        display: block;\n" + "        margin: 0 auto !important;\n"
					+ "        /* makes it centered */\n" + "        max-width: 580px;\n" + "        padding: 10px;\n"
					+ "        width: 580px; \n" + "      }\n" + "\n"
					+ "      /* This should also be a block element, so that it will fill 100% of the .container */\n"
					+ "      .content {\n" + "        box-sizing: border-box;\n" + "        display: block;\n"
					+ "        margin: 0 auto;\n" + "        max-width: 580px;\n" + "        padding: 10px; \n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          HEADER, FOOTER, MAIN\n" + "      ------------------------------------- */\n"
					+ "      .main {\n" + "        background: #ffffff;\n" + "        border-radius: 3px;\n"
					+ "        width: 100%; \n" + "      }\n" + "\n" + "      .wrapper {\n"
					+ "        box-sizing: border-box;\n" + "        padding: 20px; \n" + "      }\n" + "\n"
					+ "      .content-block {\n" + "        padding-bottom: 10px;\n" + "        padding-top: 10px;\n"
					+ "      }\n" + "\n" + "      .footer {\n" + "        clear: both;\n" + "        margin-top: 10px;\n"
					+ "        text-align: center;\n" + "        width: 100%; \n" + "      }\n" + "        .footer td,\n"
					+ "        .footer p,\n" + "        .footer span,\n" + "        .footer a {\n"
					+ "          color: #999999;\n" + "          font-size: 12px;\n" + "          text-align: center; \n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n" + "          TYPOGRAPHY\n"
					+ "      ------------------------------------- */\n" + "      h1,\n" + "      h2,\n" + "      h3,\n"
					+ "      h4 {\n" + "        color: #000000;\n" + "        font-family: sans-serif;\n"
					+ "        font-weight: 400;\n" + "        line-height: 1.4;\n" + "        margin: 0;\n"
					+ "        margin-bottom: 30px; \n" + "      }\n" + "\n" + "      h1 {\n" + "        font-size: 35px;\n"
					+ "        font-weight: 300;\n" + "        text-align: center;\n"
					+ "        text-transform: capitalize; \n" + "      }\n" + "\n" + "      p,\n" + "      ul,\n"
					+ "      ol {\n" + "        font-family: sans-serif;\n" + "        font-size: 14px;\n"
					+ "        font-weight: normal;\n" + "        margin: 0;\n" + "        margin-bottom: 15px; \n"
					+ "      }\n" + "        p li,\n" + "        ul li,\n" + "        ol li {\n"
					+ "          list-style-position: inside;\n" + "          margin-left: 5px; \n" + "      }\n" + "\n"
					+ "      a {\n" + "        color: #3498db;\n" + "        text-decoration: underline; \n" + "      }\n"
					+ "\n" + "      /* -------------------------------------\n" + "          BUTTONS\n"
					+ "      ------------------------------------- */\n" + "      .btn {\n"
					+ "        box-sizing: border-box;\n" + "        width: 100%; }\n"
					+ "        .btn > tbody > tr > td {\n" + "          padding-bottom: 15px; }\n"
					+ "        .btn table {\n" + "          width: auto; \n" + "      }\n" + "        .btn table td {\n"
					+ "          background-color: #ffffff;\n" + "          border-radius: 5px;\n"
					+ "          text-align: center; \n" + "      }\n" + "        .btn a {\n"
					+ "          background-color: #ffffff;\n" + "          border: solid 1px #3498db;\n"
					+ "          border-radius: 5px;\n" + "          box-sizing: border-box;\n"
					+ "          color: #3498db;\n" + "          cursor: pointer;\n" + "          display: inline-block;\n"
					+ "          font-size: 14px;\n" + "          font-weight: bold;\n" + "          margin: 0;\n"
					+ "          padding: 12px 25px;\n" + "          text-decoration: none;\n"
					+ "          text-transform: capitalize; \n" + "      }\n" + "\n" + "      .btn-primary table td {\n"
					+ "        background-color: #3498db; \n" + "      }\n" + "\n" + "      .btn-primary a {\n"
					+ "        background-color: #3498db;\n" + "        border-color: #3498db;\n"
					+ "        color: #ffffff; \n" + "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          OTHER STYLES THAT MIGHT BE USEFUL\n" + "      ------------------------------------- */\n"
					+ "      .last {\n" + "        margin-bottom: 0; \n" + "      }\n" + "\n" + "      .first {\n"
					+ "        margin-top: 0; \n" + "      }\n" + "\n" + "      .align-center {\n"
					+ "        text-align: center; \n" + "      }\n" + "\n" + "      .align-right {\n"
					+ "        text-align: right; \n" + "      }\n" + "\n" + "      .align-left {\n"
					+ "        text-align: left; \n" + "      }\n" + "\n" + "      .clear {\n" + "        clear: both; \n"
					+ "      }\n" + "\n" + "      .mt0 {\n" + "        margin-top: 0; \n" + "      }\n" + "\n"
					+ "      .mb0 {\n" + "        margin-bottom: 0; \n" + "      }\n" + "\n" + "      .preheader {\n"
					+ "        color: transparent;\n" + "        display: none;\n" + "        height: 0;\n"
					+ "        max-height: 0;\n" + "        max-width: 0;\n" + "        opacity: 0;\n"
					+ "        overflow: hidden;\n" + "        mso-hide: all;\n" + "        visibility: hidden;\n"
					+ "        width: 0; \n" + "      }\n" + "\n" + "      .powered-by a {\n"
					+ "        text-decoration: none; \n" + "      }\n" + "\n" + "      hr {\n" + "        border: 0;\n"
					+ "        border-bottom: 1px solid #f6f6f6;\n" + "        margin: 20px 0; \n" + "      }\n" + "\n"
					+ "      /* -------------------------------------\n"
					+ "          RESPONSIVE AND MOBILE FRIENDLY STYLES\n"
					+ "      ------------------------------------- */\n"
					+ "      @media only screen and (max-width: 620px) {\n" + "        table[class=body] h1 {\n"
					+ "          font-size: 28px !important;\n" + "          margin-bottom: 10px !important; \n"
					+ "        }\n" + "        table[class=body] p,\n" + "        table[class=body] ul,\n"
					+ "        table[class=body] ol,\n" + "        table[class=body] td,\n"
					+ "        table[class=body] span,\n" + "        table[class=body] a {\n"
					+ "          font-size: 16px !important; \n" + "        }\n" + "        table[class=body] .wrapper,\n"
					+ "        table[class=body] .article {\n" + "          padding: 10px !important; \n" + "        }\n"
					+ "        table[class=body] .content {\n" + "          padding: 0 !important; \n" + "        }\n"
					+ "        table[class=body] .container {\n" + "          padding: 0 !important;\n"
					+ "          width: 100% !important; \n" + "        }\n" + "        table[class=body] .main {\n"
					+ "          border-left-width: 0 !important;\n" + "          border-radius: 0 !important;\n"
					+ "          border-right-width: 0 !important; \n" + "        }\n"
					+ "        table[class=body] .btn table {\n" + "          width: 100% !important; \n" + "        }\n"
					+ "        table[class=body] .btn a {\n" + "          width: 100% !important; \n" + "        }\n"
					+ "        table[class=body] .img-responsive {\n" + "          height: auto !important;\n"
					+ "          max-width: 100% !important;\n" + "          width: auto !important; \n" + "        }\n"
					+ "      }\n" + "\n" + "      /* -------------------------------------\n"
					+ "          PRESERVE THESE STYLES IN THE HEAD\n" + "      ------------------------------------- */\n"
					+ "      @media all {\n" + "        .ExternalClass {\n" + "          width: 100%; \n" + "        }\n"
					+ "        .ExternalClass,\n" + "        .ExternalClass p,\n" + "        .ExternalClass span,\n"
					+ "        .ExternalClass font,\n" + "        .ExternalClass td,\n" + "        .ExternalClass div {\n"
					+ "          line-height: 100%; \n" + "        }\n" + "        .apple-link a {\n"
					+ "          color: inherit !important;\n" + "          font-family: inherit !important;\n"
					+ "          font-size: inherit !important;\n" + "          font-weight: inherit !important;\n"
					+ "          line-height: inherit !important;\n" + "          text-decoration: none !important; \n"
					+ "        }\n" + "        #MessageViewBody a {\n" + "          color: inherit;\n"
					+ "          text-decoration: none;\n" + "          font-size: inherit;\n"
					+ "          font-family: inherit;\n" + "          font-weight: inherit;\n"
					+ "          line-height: inherit;\n" + "        }\n" + "        .btn-primary table td:hover {\n"
					+ "          background-color: #34495e !important; \n" + "        }\n"
					+ "        .btn-primary a:hover {\n" + "          background-color: #34495e !important;\n"
					+ "          border-color: #34495e !important; \n" + "        } \n" + "      }\n" + "\n"
					+ "    </style>\n" + "  </head>\n" + "  <body class=\"\">\n"
					+ "    <span class=\"preheader\">This is preheader text. Some clients will show this text as a preview.</span>\n"
					+ "    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\n"
					+ "      <tr>\n" + "        <td>&nbsp;</td>\n" + "        <td class=\"container\">\n"
					+ "          <div class=\"content\">\n" + "\n" + "            <!-- START CENTERED WHITE CONTAINER -->\n"
					+ "            <table role=\"presentation\" class=\"main\">\n"
					+ "                    <img src=\"https://imgur.com/AtqH0h5\"></a>\n" + "\n"
					+ "              <!-- START MAIN CONTENT AREA -->\n" + "              <tr>\n"
					+ "                <td class=\"wrapper\">\n"
					+ "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                    <tr>\n" + "                      <td>\n"
					+ "                        <p>Bonjour ,</p>\n"
					+ "                        <p>Merci pour l'utilsation de notre application CONSOMI TOUNSI.</p>\n"
					+ "                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\n"
					+ "                          <tbody>\n" + "                            <tr>\n"
					+ "                              <td align=\"left\">\n"
					+ "                                <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                                  <tbody>\n" + "                                  </tbody>\n"
					+ "                                </table>\n" + "                              </td>\n"
					+ "                            </tr>\n" + "                          </tbody>\n"
					+ "                        </table>\n" + "                        <p></p>\n"
					+ "                        <p>Aujourd'hui c'est la date fin de votre publicité <b> \n"+p.getNom()+" "+"</b>, qui est débutée le <b>\n" + p.getDateDebut() +"</b></p>\n"
					+ "                      <p>Avec un CPM (Coût par mille): <b>\n" + cpm  +" DT</b></p>"
					+ "                      <p>Et un Coût Total :  <b>\n" + p.getCout() +" DT</b></p>"
					+ "                        <p>Cordialement.</p>\n"
					+ "                        <p>Bienvenue! CONSOMI TOUNSI Group.</p>\n" + "                      </td>\n"
					+ "                    </tr>\n" + "                  </table>\n" + "                </td>\n"
					+ "              </tr>\n" + "\n" + "            <!-- END MAIN CONTENT AREA -->\n"
					+ "            </table>\n" + "            <!-- END CENTERED WHITE CONTAINER -->\n" + "\n"
					+ "            <!-- START FOOTER -->\n" + "            <div class=\"footer\">\n"
					+ "              <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n"
					+ "                <tr>\n" + "                  <td class=\"content-block\">\n"
					+ "                    <span class=\"apple-link\">CONSOMI TOUNSI,El-ghazela, Tunisie</span>\n"
					+ "                  </td>\n" + "                </tr>\n" + "                <tr>\n"
					+ "                  <td class=\"content-block powered-by\">\n"
					+ "                    Powered by <a href=\"http://htmlemail.io\">HTMLemail</a>.\n"
					+ "                  </td>\n" + "                </tr>\n" + "              </table>\n"
					+ "            </div>\n" + "            <!-- END FOOTER -->\n" + "\n" + "          </div>\n"
					+ "        </td>\n" + "        <td>&nbsp;</td>\n" + "      </tr>\n" + "    </table>\n" + "  </body>\n"
					+ "</html>\"");
			String messaage = buf.toString();
			//// ********************************////
			MimeMessage message = javaMailSender.createMimeMessage();
			   MimeMessageHelper helper = new MimeMessageHelper(message, true);
			   helper.setTo(p.getEmailProprietaire());
			   helper.setFrom("consommis.toounsi.619@gmail.com");
			   helper.setSubject("Fin Contrat Publicité");
			   helper.setText(messaage, messaage);
			  javaMailSender.send(message);	
		}
	}

}
