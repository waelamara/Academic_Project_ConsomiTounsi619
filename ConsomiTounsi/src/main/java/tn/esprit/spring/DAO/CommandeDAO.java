package tn.esprit.spring.DAO;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Repository.CommandeRepository;
@Service
public class CommandeDAO {
	@Autowired
	CommandeRepository commandeRepository;
	public Commande save (Commande c)
	{
		c.setDate(LocalDate.now());
		return commandeRepository.save(c);
	}

}
