package tn.esprit.spring.Controller.Forum;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.Forum.Vus;
import tn.esprit.spring.Service.Forum.IVusService;

@RestController
@RequestMapping("/VusSujet")
public class VusController {

@Autowired
IVusService ivusService;

@PostMapping("/ajoutervus/{sujetId}/{userId}")
public  ResponseEntity<?>  ajouterVus( @PathVariable(value = "sujetId") Long sujetid,
		                              @PathVariable(value = "userId") Long userid,
		                               Vus v ) {
	   ivusService.ajouterVus(v, sujetid, userid);
	   return ResponseEntity.created(null).body(v);
	  }

@GetMapping("/countvus/{sujetId}")
public ResponseEntity<?> countVus(@PathVariable(value = "sujetId") Long sujetId) {
	int x =ivusService.countVus(sujetId);
	return 	ResponseEntity.ok().body(x);
}

@PutMapping(value = "/editvus/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> updateVus(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivusService.UpdateVus(idsujet,iduser);
	return ResponseEntity.ok().build();
}
@GetMapping("/verification/{sujetId}/{iduser}")
public ResponseEntity<?> verificationVus(@PathVariable(value = "sujetId") Long sujetId,
									 @PathVariable("iduser") Long iduser) {
	return 	ResponseEntity.ok().body(ivusService.verificationVus( iduser, sujetId));
}
}
