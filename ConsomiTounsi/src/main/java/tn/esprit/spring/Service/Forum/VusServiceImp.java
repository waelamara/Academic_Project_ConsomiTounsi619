package tn.esprit.spring.Service.Forum;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Forum.Vus;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;
import tn.esprit.spring.Repository.Forum.VusRepository;

@Service

public class VusServiceImp implements IVusService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SujetRepository sujetRepository;
	@Autowired
	private VusRepository vusRepository;

	@Override
	public int ajouterVus(Vus v, Long sujetId, Long userId) {
		
		v.setNbVus(1);
		Sujet sujet = sujetRepository.findById(sujetId).get();
		User user = userRepository.findById(userId).orElse(null);
		if (user==null){
			v.setIdSujet(sujet);
			int x = countVus(sujetId);
			sujet.setNbVue(x);
			LocalDate localDate = LocalDate.now();
			v.setDateAjout(java.sql.Date.valueOf(localDate));
			vusRepository.save(v);
			sujetRepository.save(sujet);
		}else{
		v.setIdUser(user);
		v.setIdSujet(sujet);
		int x = countVus(sujetId);
		sujet.setNbVue(x);
		LocalDate localDate = LocalDate.now();
		v.setDateAjout(java.sql.Date.valueOf(localDate));
		vusRepository.save(v);
		sujetRepository.save(sujet);}
		return 0;
	}

	@Transactional
	@Override
	public void UpdateVus(Long sujetId, Long userId) {
		Vus v = vusRepository.getVusBySujetAndUser(sujetId, userId);
		Sujet sujet = sujetRepository.findById(sujetId).get();
		if (sujet.getIdUser().getId() == userId) {
			if (v.getNbVus() < 10) {
				v.setNbVus(v.getNbVus() + 1);
				int x = countVus(sujetId);
				sujet.setNbVue(x);
				sujetRepository.save(sujet);
			} else
				v.setNbVus(10);
		} else
			v.setNbVus(v.getNbVus() + 1);
		int x = countVus(sujetId);
		sujet.setNbVue(x);
		LocalDate localDate = LocalDate.now();
		v.setDateAjout(java.sql.Date.valueOf(localDate));
		vusRepository.save(v);
		sujetRepository.save(sujet);
	}

	@Transactional
	@Override
	public int countVus(Long sujetId) {
		Integer x;
		x = vusRepository.countVus(sujetId);
		if (x == null){
			x=0;
			Sujet sujet = sujetRepository.findById(sujetId).get();
			sujet.setNbVue(x);
			sujetRepository.save(sujet);
			return x;}
		else{
			Sujet sujet = sujetRepository.findById(sujetId).get();
			sujet.setNbVue(x);
			sujetRepository.save(sujet);
		return x;}
	}

	@Override
	public Boolean verificationVus(Long userId, Long sujetId) {
		Vus v = vusRepository.getVusBySujetAndUser(sujetId, userId);
		if (v == null)
			return false;
		return true;
	}

	@Override
	public List<Long> mostPopularPost() {
		
		return vusRepository.mostidsujetviews();
	}

}
