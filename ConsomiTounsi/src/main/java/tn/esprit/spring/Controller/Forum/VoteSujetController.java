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
public ResponseEntity<?> countLike(@PathVariable(value = "sujetId") Long sujetId) {
	int x =ivoteSujetservice.countlike(sujetId);
	return 	ResponseEntity.ok().body(x);
}
@GetMapping("/countd/{sujetId}")
public ResponseEntity<?> counteDislike(@PathVariable(value = "sujetId") Long sujetId) {
	int x =ivoteSujetservice.countdislik(sujetId);
	return 	ResponseEntity.ok().body(x);
}

@GetMapping("/verification/{sujetId}/{iduser}")
public ResponseEntity<?> verificationVote(@PathVariable(value = "sujetId") Long sujetId,
									 @PathVariable("iduser") Long iduser) {
	return 	ResponseEntity.ok().body(ivoteSujetservice.verificationvote(sujetId, iduser));
}
@GetMapping("/afficher/{sujetId}/{iduser}")
public ResponseEntity<?> afficherVote(@PathVariable(value = "sujetId") Long sujetId,
									 @PathVariable("iduser") Long iduser) {
	VoteSujet vote;
	vote=ivoteSujetservice.getVote(sujetId, iduser) ;
	return 	ResponseEntity.ok().body(vote);
}

@GetMapping("/afficher/{sujetId}")
public ResponseEntity<?> affichervVoteOfSujet(@PathVariable(value = "sujetId") Long sujetId){
	List<VoteSujet> vote=new ArrayList<>();
	vote=ivoteSujetservice.getVoteOfSujet(sujetId) ;
	return 	ResponseEntity.ok().body(vote);
}

@GetMapping("/afficherNomsUser/{sujetId}")
public ResponseEntity<?> afficherNomdesUserVote(@PathVariable(value = "sujetId") Long sujetId){
	List<String> noms=new ArrayList<>();
	noms=ivoteSujetservice.findNomdesUsersVoter(sujetId);
	return 	ResponseEntity.ok().body(noms);
}

@PutMapping(value = "/editvotel/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> updateLike(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivoteSujetservice.Updatelike(idsujet,iduser);
	return ResponseEntity.ok().build();
}

@PutMapping(value = "/editvoted/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> updateDislike(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivoteSujetservice.Updatedislike(idsujet,iduser);
	return ResponseEntity.ok().build();
}

@PutMapping(value = "/delete/{idsujet}/{iduser}") 
@ResponseBody
public ResponseEntity<?> deleteVote(@PathVariable("idsujet") Long idsujet, @PathVariable("iduser") Long iduser) {
	ivoteSujetservice.deletevoteById(idsujet, iduser);
	return ResponseEntity.ok().build();	
}
}
