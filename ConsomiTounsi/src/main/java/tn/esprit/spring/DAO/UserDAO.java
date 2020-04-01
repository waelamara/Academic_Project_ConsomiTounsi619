package tn.esprit.spring.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.UserRepository;


@Service
public class UserDAO {
	@Autowired
	UserRepository  UserRepository;
	
	/*Chercher un utilisateur*/
	public User findOne(long id){
	return UserRepository.getOne(id);}
	public User save(User u) {
		return UserRepository.save(u);
	}
	public List<User> findAll() {
		return UserRepository.findAll();
	}

}
