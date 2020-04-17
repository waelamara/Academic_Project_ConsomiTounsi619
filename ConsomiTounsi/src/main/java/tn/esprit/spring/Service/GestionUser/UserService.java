package tn.esprit.spring.Service.GestionUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.UserRepository;


@Service
public class UserService {
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
	
	/*Update d'un user*/
	public  User updateUser(User user)	{
		return UserRepository.save(user);
		
	}


}
