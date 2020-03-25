package tn.esprit.spring.Controller.Forum;

import java.util.ArrayList;
import java.util.List;

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

import tn.esprit.spring.DAO.Forum.IVoteSujetService;
import tn.esprit.spring.Model.Forum.VoteSujet;

@RestController
@RequestMapping("/voteSujet")
public class VoteSujetController {
@Autowired
IVoteSujetService ivoteSujetservice;
@PostMapping("/ajouter/{sujetId}/{userId}")
public ResponseEntity<?> ajouterSujet( @PathVariable(value = "sujetId") Long sujetid,
		                              @PathVariable(value = "userId") Long userid,
		                              @Valid @RequestBody VoteSujet v ) {
	ivoteSujetservice.ajouterlike( v,sujetid, userid);
	  return ResponseEntity.created(null).body(v);}
@GetMapping("/countl/{sujetId}")
public ResponseEntity<?> countlike(@PathVariable(value = "sujetId") Long sujetId) {
	int x =ivoteSujetservice.countlike(sujetId);
	return 	ResponseEntity.ok().body(x);
}
@GetMapping("/countd/{sujetId}")
public ResponseEntity<?> countedislike(@PathVariable(value = "sujetId") Long sujetId) {
	int x =ivoteSujetservice.countdislik(sujetId);
	return 	ResponseEntity.ok().body(x);
}
@GetMapping("/afficher/{sujetId}/{iduser}")
public ResponseEntity<?> affichervote(@PathVariable(value = "sujetId") Long sujetId,
									 @PathVariable("iduser") Long iduser) {
	List<VoteSujet> vote = new ArrayList<>();
	vote=ivoteSujetservice.getAllVote(sujetId, iduser) ;
	return 	ResponseEntity.ok().body(vote);
}
@PutMapping(value = "/editvotel/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> updatelike(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivoteSujetservice.Updatelike(idsujet,iduser);
	return ResponseEntity.ok().build();
}

@PutMapping(value = "/delete/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> deleteVote(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivoteSujetservice.deletevoteById(idsujet, iduser);
	return ResponseEntity.ok().build();	
}
}
