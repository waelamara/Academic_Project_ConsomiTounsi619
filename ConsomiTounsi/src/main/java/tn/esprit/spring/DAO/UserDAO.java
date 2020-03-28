package tn.esprit.spring.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
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

}
