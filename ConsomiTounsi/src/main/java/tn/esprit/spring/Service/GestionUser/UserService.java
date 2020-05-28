package tn.esprit.spring.Service.GestionUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.VerificationToken;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.VerificationTokenRepository;


@Service
public class UserService {
	@Autowired
	UserRepository  UserRepository;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	/*Chercher un utilisateur*/
	public User findOne(long id){
	return UserRepository.findById(id).get();
	}
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
	
	public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
	
	public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
	
	public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
	/*Livreur methodes oussama*/
  
	
	


}
