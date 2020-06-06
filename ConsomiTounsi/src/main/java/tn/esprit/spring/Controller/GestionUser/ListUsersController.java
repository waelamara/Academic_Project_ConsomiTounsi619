package tn.esprit.spring.Controller.GestionUser;

import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Controller.Forum.RepeatPaginator;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Service.GestionUser.UserService;

@Controller(value = "listUsersController")
@ELBeanName(value = "listUsersController")
@Join(path = "/listusers", to = "/listusers.jsf")
@Component
public class ListUsersController {
	@Autowired
	UserService UserService;
	
	private RepeatPaginator1 paginator;
	
	private String choix;
	private String cle;
	@PostConstruct
	public void init(){
		List<User> c= getAllUsers();
	paginator = new RepeatPaginator1(c);
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
