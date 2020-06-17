package tn.esprit.spring.Service.Publicite;


import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.primefaces.model.file.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface IPubliciteService {
	public Publicite save(Publicite p);
	public Publicite Add(String ProduitJson,MultipartFile file)throws JsonMappingException, JsonProcessingException,IOException, ParseException;
	public Publicite AddPub(Publicite pub,UploadedFile file) throws IOException,ParseException;
	public List<Publicite> findAll();
	public Publicite Update(Publicite p,Long id);
	public Publicite UpdatePubWithoutImage(Publicite pub) throws IOException, ParseException;
	public Publicite UpdatePubWithImage(Publicite pub, UploadedFile file) throws IOException, ParseException;
	public void Delete(Long id);
	public Publicite findOne(Long id);
	public float CalculeCoutTotalPub(String gender, String canal, int ageCibledebut, int ageCibleFin, String dateDebut,
			String dateFin, String typePub) throws ParseException;
	public int CoutSurLeNbrDeJour(String dateDebut, String dateFin) throws ParseException;
	public int DifferenceJourDateDebutEtDateFin(String dateDebut, String dateFin) throws ParseException;
	public int CoutSurCanal(String canal);
	public int CoutSurTrancheAge(int ageCibledebut, int ageCibleFin);
	public Publicite AffecterImageVideoPub(Publicite pub, MultipartFile file) throws IOException;
	public List<Publicite> findLikeName(String nom);
	public List<Publicite> findByCanal(String canal);
	public List<Publicite> findByCanalAndName(String canal,String nom);
	public Date ConvertirDate(String date) throws ParseException;
	public String TypeFile(UploadedFile file) throws IOException;
	public List<Publicite> findPubForAll();
	public List<Publicite> getPubForUserConnecter(Date UserDateNaissance,String gender);
	public void PublicityFinished() throws MessagingException;
}
