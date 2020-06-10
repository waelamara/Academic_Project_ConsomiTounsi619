package tn.esprit.spring.Controller.Produit;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Utils.AppConstants;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Service.Produit.IProduitService;

@RestController
@RequestMapping("/produit")
public class RestControllerProduit {
	@Autowired
	IProduitService iproduitService;
	@Autowired
	ControllerProduit controllerProduit;
	public static Long barcodeWithPhone=1111L;
	// http://localhost:8081/produit/ajouter/1
	//{"nomProduit":"souris Gm910","prix":"50","description":"tres bonne sensibilite ","barcode":"6190000001001","poids":"0.5","prixAchat":"30.500"}
	@PostMapping(value = "/ajouter/{idSsCategorie}")
	public Produit AjouterProduit(@PathVariable(value = "idSsCategorie") Long idSsCategorie,
			@RequestParam(value = "produit", required = true) String ProduitJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file)
			throws JsonParseException, JsonMappingException, IOException {
		return iproduitService.Add(ProduitJson, idSsCategorie, file);
	}
	
	//http://localhost:8081/produit/afficher
	@GetMapping("/afficher")
	public List<Produit> getAllProduit() {
		return iproduitService.findAll();
	}

	//http://localhost:8081/produit/delete/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteProduit(@PathVariable(value = "id") Long idProduit) {
		iproduitService.Delete(idProduit);
		return ResponseEntity.ok("Produit Supprimer avec succes");
	}

	//http://localhost:8081/produit/edit/9/3
	//{"nomProduit":"souris Gm910","prix":"50","description":"tres bonne sensibilite ","barcode":"6190000001001","poids":"0.5","prixAchat":"30.500"}
	@PutMapping("/edit/{id}/{idSsCategorie}")
	public Produit EditProduit(@PathVariable(value = "id") Long idProduit,
			@PathVariable(value = "idSsCategorie") Long idSsCategorie,
			@RequestParam(value = "produit", required = true) String ProduitJson,
			@RequestParam(required = true, value = AppConstants.EMPLOYEE_FILE_PARAM) List<MultipartFile> file) 
			throws JsonMappingException, JsonProcessingException, IOException {
		return iproduitService.Update(idProduit, idSsCategorie, ProduitJson,file);
	}

	//http://localhost:8081/produit/recherche/clavier
	@GetMapping("/recherche/{nom}")
	public List<Produit> findLikeNameM(@PathVariable(value = "nom") String name) {
		return iproduitService.findLikeName(name);
	}

	//http://localhost:8081/produit/ssCategorie/1
	@GetMapping("/ssCategorie/{idSsCategorie}")
	public List<Produit> findProduitSsCategorie(@PathVariable(value = "idSsCategorie") Long idSsCategorie) {
		return iproduitService.findProduitSsCategorie(idSsCategorie);
	}

	//http://localhost:8081/produit/sCategorie/1
	@GetMapping("/sCategorie/{idSCategorie}")
	public List<Produit> findProduitSCategorie(@PathVariable(value = "idSCategorie") Long idSCategorie) {
		return iproduitService.findProduitSCategorie(idSCategorie);
	}

	//http://localhost:8081/produit/Categorie/1
	@GetMapping("/Categorie/{idCategorie}")
	public List<Produit> findProduitCategorie(@PathVariable(value = "idCategorie") Long idCategorie) {
		return iproduitService.findProduitCategorie(idCategorie);
	}

	@RequestMapping(value = "/barcode/{barcode}")
	public Long AjouterProduit(@PathVariable(value = "barcode") Long Bcode)  {
		System.out.println(Bcode);
		System.out.println(Bcode);
		setBarcodeWithPhone(Bcode);
		controllerProduit.setBarcode(Bcode);
		return Bcode;
		
	}

	public static Long getBarcodeWithPhone() {
		return barcodeWithPhone;
	}

	public static void setBarcodeWithPhone(Long barcodeWithPhone) {
		RestControllerProduit.barcodeWithPhone = barcodeWithPhone;
	}
	
	
	
}
