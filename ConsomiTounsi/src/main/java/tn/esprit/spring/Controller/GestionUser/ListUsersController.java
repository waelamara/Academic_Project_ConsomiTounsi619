package tn.esprit.spring.Controller.GestionUser;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Controller.Forum.RepeatPaginator;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.UserService;
import tn.esprit.spring.payload.response.MessageResponse;

@Controller(value = "listUsersController")
@ELBeanName(value = "listUsersController")
@Join(path = "/listusers", to = "/listusers.jsf")
@Component
public class ListUsersController {
	@Autowired
	UserService UserService;
	
	
	@Autowired
	UserRepository UserRepository;
	private RepeatPaginator1 paginator;
	
	private String choix;
	private String cle;
	@PostConstruct
	public void init(){
		List<User> c= getAllUsers();
	paginator = new RepeatPaginator1(c);
}
	
	public List<User> getNeufUsers()
	{
		return UserRepository.getNewUsers();
	}
	
	
	public List<User> getUserSelonChoix(String choixx, String clee)
	{
		
		System.out.println(UserService.getUserSelonChoix(choix, cle));
		System.out.println(cle);

		return UserService.getUserSelonChoix(choix, cle);
	}
	public List<User> getUserSelonEmail(String choixx, String clee)
	{
		return UserService.getUserSelonChoix(choix, cle);
	}
	@Transactional
	public String BanUser(Long id)
	{
		
		System.out.println(id);
		User U = UserService.findOne(id);
		U.setEtatAcc(false);
		
		UserService.updateUser(U);
		return "/listusers.xhtml";
		
	}
	@Transactional
	public String UnBanUser(Long id)
	{
		
		System.out.println(id);
		User U = UserService.findOne(id);
		U.setEtatAcc(true);
		
		UserService.updateUser(U);
		return "/listusers.xhtml";
		
	}
	public String getEtatAcc(String username)
	{
		
		
		User U = UserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		if(U.getEtatAcc()==true)
			return "Active";
		else return "Banned";
			
		
	}
	


	public RepeatPaginator1 getPaginator() {
		return paginator;
	}






	public void setPaginator(RepeatPaginator1 paginator) {
		this.paginator = paginator;
	}






	public String getChoix() {
		return choix;
	}






	public void setChoix(String choix) {
		this.choix = choix;
	}






	public String getCle() {
		return cle;
	}






	public void setCle(String cle) {
		this.cle = cle;
	}






	public List<User> getAllUsers(){
		return UserService.getAllUsers();
	}

}
