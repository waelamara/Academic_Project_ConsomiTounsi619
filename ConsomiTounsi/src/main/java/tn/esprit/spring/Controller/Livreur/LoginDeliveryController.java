package tn.esprit.spring.Controller.Livreur;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.security.jwt.JwtUtils;

@Controller(value = "LoginDeliveryController")
@ELBeanName(value = "LoginDeliveryController")
@Join(path = "/LoginDeliveryController", to = "/loginDelivery.jsf")
@Component
public class LoginDeliveryController {
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	private String login; 
	private String password;
	private Boolean loggedIn=false;
	

}
