package tn.esprit.spring.Service.Charite;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Utils.AppConstants;
import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.Charite.EventsRepository;
import tn.esprit.spring.Repository.Charite.PubRepository;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Service
public class PubDAO {
	@Autowired
	PubRepository publiciteRepository;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;
	@Autowired
	EventsDAO eventDAO;
	private UploadedFiles files;
	@Autowired
	private EventsRepository eventsRepository;
	public List<Pub> findLikeName(String nom) {
		return publiciteRepository.findLikeName(nom);
	}

	public Pub save(Pub p) {
		return publiciteRepository.save(p);
	
	}
	
	public List<Pub> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Pub c) {
		publiciteRepository.delete(c);
	}

	public Pub findOne(Long id) {
		return publiciteRepository.getOne(id);
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
	
	@Transactional
	public void savePub(Long ideventss,Pub p, UploadedFiles files) {
		Events e = eventDAO.findOne(ideventss);
		
	
		for (UploadedFile f : files.getFiles()) {
         	String newFileName = fileStorageServiceImpl.UploadImages(f);
         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
			p.setImage(fileDownloadUri);
			publiciteRepository.save(p);
		}
		e.setPublicite(p);
		eventsRepository.save(e);
		
		
		
	}
	@Transactional
	public void updatePub(Pub p, UploadedFiles files) {
		
	
		for (UploadedFile f : files.getFiles()) {
         	String newFileName = fileStorageServiceImpl.UploadImages(f);
         	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(AppConstants.DOWNLOAD_PATH).path(newFileName).toUriString();
			p.setImage(fileDownloadUri);
			publiciteRepository.save(p);
		}
	}
	public Date ConvertirDate(String date) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf1.parse(date));
		calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
	}
	

}
