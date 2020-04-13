package tn.esprit.spring.Service.Forum;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.ProduitRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Forum.CategorieSujetRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;

@Service
public class SujetServiceImpl implements ISujetService {
	@Autowired
	private  SujetRepository sujetRepository ;
	@Autowired
	private  CategorieSujetRepository categorieSujetRepository; 
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProduitRepository produitRepository;
	
	@Override
	public int ajouterSujet(Sujet s,Long categId,Long userId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		User user= userRepository.findById(userId).get();
		s.setCategorieSujet(categS);
		s.setIdUser(user);
		LocalDate localDate = LocalDate.now();
		s.setDateAjout(java.sql.Date.valueOf(localDate));
		s.setNbLike(0);
		s.setNbDislike(0);
		sujetRepository.save(s);
		return s.getId().intValue();	
	}
	
	
	@Override
	public List<Sujet> getAllSujets() {
		return sujetRepository.findAllOrderbyDate();
	}

	@Override
	public int deleteSujetById(Long sujetId,Long userId) {
		Sujet sujet = sujetRepository.findById(sujetId).get();
		if(sujet.getIdUser().getId()== userId)
		{
			 sujetRepository.deleteById(sujetId);	 
			 return 1;
		}
			 return 0;  
	}

	@Override
	public List<Sujet> findSujetbyName(String name) {
		return  sujetRepository.findSujetbyName(name);
	}

	@Override
	public Sujet findOne(Long sujetId) {
		return sujetRepository.findById(sujetId).get();
	}

	@Override
	public int modifierDescription(String desc, Long sujetId,Long userId ) {
	Sujet sujet =sujetRepository.findById(sujetId).get();
	if(sujet.getIdUser().getId()== userId)
	{
		sujet.setDescription(desc);
		sujetRepository.save(sujet);	 
		 return 1;
	}
		 return 0;  
	
		
	}

	@Override
	public void affecterSujetACategS(Long sujId, Long categId) {
		CategorieSujet categS = categorieSujetRepository.findById(categId).get();
		Sujet sujet=sujetRepository.findById(sujId).get();
		sujet.setCategorieSujet(categS);
		sujetRepository.save(sujet);	
	}

	@Override
	public List<Sujet> getAllSujetNamesByCategorie(Long categId) {
		List<Sujet> sujetNames = new ArrayList<>();
		sujetNames=sujetRepository.findSujetbycatgID(categId);
		 return sujetNames;
		 
	}

	@Override
	public List<Sujet> findSujetbyUser(Long userid) {
		User user = userRepository.findById(userid).get();
		List<Sujet> sujets = new ArrayList<>();
		for(Sujet suj :user.getSujets())
			sujets.add(suj);
			return sujets;
			
	}

	@Override
	public String findNamebySujet(Long sujetId) {
		Sujet sujet=sujetRepository.findById(sujetId).get();
		return sujet.getIdUser().getFirstName()+" "+sujet.getIdUser().getLastName();
	}
	
	@Override
	public User client_gangnant() {
		List<String> ids = userRepository.findClient_pt_100();
		String separ = ",";
		String res = String.join(separ, ids);
		String motcommentaire1[] = res.split(",");
		int r1 = (int) (Math.random() * (motcommentaire1.length));
		String name1 = motcommentaire1[r1];
		long random = Long.parseLong(name1);
		User clientgagnant = userRepository.findById(random).get();
		// a.setNum_carte_fidelity(0);
		// clientrepository.save(a);
		return clientgagnant;
		}
	
	@Override
	public Produit produit_gangant()  {
		User clientgagnant= client_gangnant();
	
		String interets = clientgagnant.getInteret();

		String linterets[] = interets.split(",");
		int r = (int) (Math.random() * (linterets.length));
		String name = linterets[r];
	
		List<String> idproduits = produitRepository.findProduitCategorbyName(name);
		
		String delim2 = ",";
		String res2 = String.join(delim2, idproduits);
		
		String lproduits[] = res2.split(",");
		int r2 = (int) (Math.random() *(lproduits.length));
			String name2 = lproduits[r2];
			long random = Long.parseLong(name2);
		Produit p1 = produitRepository.findById(random).get();
		return p1;
		
	}
}
