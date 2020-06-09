package tn.esprit.spring.Service.GestionUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Charite.ChariteRepository;

@Service
public class ChartService {
	@Autowired
	UserRepository  userRepository;
	@Autowired 
	CommandeRepository commandeRepository;
	@Autowired
	ChariteRepository chariteRepository;
	
	public Long getNombreOrders()
	{
		return commandeRepository.NombreCommade();
	}
	public float getTotalSales()
	{
		return commandeRepository.TotalSales();
	}
	public float getTotalDonation()
	{
		return chariteRepository.TotalDonation();
	}

}
