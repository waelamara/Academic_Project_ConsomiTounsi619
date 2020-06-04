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
	
	private RepeatPaginator paginator;
	@PostConstruct
	public void init(){
		List<User> c= getAllUsers();
	paginator = new RepeatPaginator(c);
}
	
	
	



	public RepeatPaginator getPaginator() {
		return paginator;
	}






	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}






	public List<User> getAllUsers(){
		return UserService.getAllUsers();
	}

}
