package tn.esprit.spring.Service.Publicite;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.PubliciteRepository;
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
		return publiciteRepository.save(PubWithImg);

	}

	public int NbrUserFemme() {
		return 0;
	}

	public int NbrUserHomme() {
		return 0;
	}

	public int NbrUserTotal() {
		return 0;
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

	public int DifferenceJourDateDebutEtDateFin(String dateDebut, String dateFin) throws ParseException {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
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
		int TrancheAge = ageCibleFin - ageCibledebut;
		int cout = 500;
		for (int i = 1; i <= TrancheAge; i++) {
			cout -= 10;
		}
		return cout;
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

}
