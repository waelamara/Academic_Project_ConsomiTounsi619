package tn.esprit.spring.Controller.Panier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.CadeauUser;
import tn.esprit.spring.Service.Panier.CadeauUserImpl;

@RestController
@RequestMapping("/Cadeau")
public class RestCadeauUserController {
@Autowired
CadeauUserImpl cadeauUserImpl;
@PostMapping("/ajouter")
public void save ()
{
	
 cadeauUserImpl.save();
}
@PutMapping("/choisir/{idUser}")
public String CadeauUser(@PathVariable(value = "idUser")Long idUser)
{
	return cadeauUserImpl.CadeauUser(idUser);
}

@GetMapping("/montantCadeau/{code}")
public float montantCadeau(@PathVariable(value = "code")String code)
{
	return cadeauUserImpl.montantCadeau(code);
}
}