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

import tn.esprit.spring.Model.Forum.Vote;
import tn.esprit.spring.Service.Forum.IVoteCommentaire;

@RestController
@RequestMapping("/voteCom")
public class VoteCommentarie  {
@Autowired
IVoteCommentaire ivotecommentaire;

	@PostMapping("/ajouterl/{comId}/{userId}")
	public ResponseEntity<?> ajouterLike( @PathVariable(value = "comId") Long comid,
			                              @PathVariable(value = "userId") Long userid,
			                              @Valid @RequestBody Vote v ) {
		ivotecommentaire.ajouterlike(v, comid, userid);
		  return ResponseEntity.created(null).body(v);}
	
	@PostMapping("/ajouterd/{comId}/{userId}")
	public ResponseEntity<?> ajouterDislike( @PathVariable(value = "comId") Long comid,
			                              @PathVariable(value = "userId") Long userid,
			                              @Valid @RequestBody Vote v ) {
		ivotecommentaire.ajouterdislike( v,comid, userid);
		  return ResponseEntity.created(null).body(v);
		  }
	
	@GetMapping("/countl/{comId}")
	public ResponseEntity<?> countLike(@PathVariable(value = "comId") Long comId) {
		int x =ivotecommentaire.countlikeCom(comId);
		return 	ResponseEntity.ok().body(x);
	}
	@GetMapping("/countd/{comId}")
	public ResponseEntity<?> counteDislike(@PathVariable(value = "comId") Long comId) {
		int x =ivotecommentaire.countdislikCom(comId);
		return 	ResponseEntity.ok().body(x);
	}
	
	@GetMapping("/verification/{comId}/{iduser}")
	public ResponseEntity<?> verificationVote(@PathVariable(value = "comId") Long comId,
										 @PathVariable("iduser") Long userId) {
		return 	ResponseEntity.ok().body(ivotecommentaire.verificationvote(comId, userId));
	}
	@GetMapping("/afficher/{comId}/{iduser}")
	public ResponseEntity<?> afficherVote(@PathVariable(value = "comId") Long comId,
										 @PathVariable("iduser") Long iduser) {
		Vote vote=ivotecommentaire.getVote(comId, iduser) ;
		return 	ResponseEntity.ok().body(vote);
	}
	
	@GetMapping("/afficher/{comId}")
	public ResponseEntity<?> affichervVoteOfSujet(@PathVariable(value = "comId") Long comId){
		List<Vote> vote=new ArrayList<>();
		vote=ivotecommentaire.getVoteOfCom(comId) ;
		return 	ResponseEntity.ok().body(vote);
	}
	
	@GetMapping("/afficherNomsUser/{comId}")
	public ResponseEntity<?> afficherNomdesUserVote(@PathVariable(value = "comId") Long comId){
		List<String> noms=new ArrayList<>();
		noms=ivotecommentaire.findNomdesUsersVoter(comId);
		return 	ResponseEntity.ok().body(noms);
	}
	
	@PutMapping(value = "/editvotel/{comId}/{iduser}") 
	@ResponseBody
	public ResponseEntity<?> updateLike(@PathVariable("comId") Long comId, @PathVariable("iduser") Long iduser) {
		ivotecommentaire.Updatelike(comId,iduser);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/editvoted/{comId}/{iduser}") 
	@ResponseBody
	public ResponseEntity<?> updateDislike(@PathVariable("comId") Long comId, @PathVariable("iduser") Long iduser) {
		ivotecommentaire.Updatedislike(comId,iduser);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value = "/delete/{comId}/{iduser}") 
	@ResponseBody
	public ResponseEntity<?> deleteVote(@PathVariable("comId") Long comId, @PathVariable("iduser") Long iduser) {
		ivotecommentaire.deletevoteById(comId, iduser);
		return ResponseEntity.ok().build();	
	}
	
	}
