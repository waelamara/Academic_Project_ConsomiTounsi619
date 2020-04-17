package tn.esprit.spring.Service.Charite;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Charite.Pub;
import tn.esprit.spring.Repository.Charite.PubRepository;

@Service
public class PubDAO {
	@Autowired
	PubRepository publiciteRepository;

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
}
