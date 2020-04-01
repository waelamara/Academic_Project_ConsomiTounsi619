package tn.esprit.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.LivraisonRepository;

@Service
public class LivraisonDAO {
	@Autowired
	LivraisonRepository LivraisonRepository;

}
