package tn.esprit.spring.Service.Publicite;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import tn.esprit.spring.Model.Publicite.Publicite;

public interface IPubliciteService {
	public Publicite save(Publicite p);
	public Publicite Add(String ProduitJson,MultipartFile file)throws JsonMappingException, JsonProcessingException,IOException, ParseException;
	public List<Publicite> findAll();
	public Publicite Update(Publicite p,Long id);
	public void Delete(Long id);
	public Publicite findOne(Long id);
	
}
