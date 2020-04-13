package tn.esprit.spring.Service.Publicite;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Utils.AppConstants;
import tn.esprit.spring.Model.Publicite.Publicite;
import tn.esprit.spring.Repository.PubliciteRepository;
import tn.esprit.spring.Service.Produit.FileStorageServiceImpl;

@Service
public class PubliciteServiceImpl implements IPubliciteService {
	@Autowired
	PubliciteRepository publiciteRepository;
	@Autowired
	FileStorageServiceImpl fileStorageServiceImpl;

	ObjectMapper objectMapper = new ObjectMapper();
	
	public Publicite save(Publicite p) {
		return publiciteRepository.save(p);
	}

	public List<Publicite> findAll() {
		return publiciteRepository.findAll();
	}

	public void Delete(Long id) {
		Publicite p2 = findOne(id);
		publiciteRepository.delete(p2);
	}

	public Publicite findOne(Long id) {
		return publiciteRepository.getOne(id);
	}

	public Publicite Update(Publicite p, Long id) {
		Publicite p2 = findOne(id);
		p2.setCanal(p.getCanal());
		p2.setNom(p.getNom());
		p2.setDateDebut(p.getDateDebut());
		p2.setDateFin(p.getDateFin());
		p2.setNbrInitialVueCible(p.getNbrInitialVueCible());
		p2.setNbrFinalVue(p.getNbrFinalVue());
		p2.setCout(p.getCout());
		Publicite PubliciteUpdated = save(p2);
		return PubliciteUpdated;
	}


	public Publicite Add(String ProduitJson, MultipartFile file) throws JsonMappingException, JsonProcessingException,IOException {
			Publicite pub = objectMapper.readValue(ProduitJson, Publicite.class);
			String fileName = fileStorageServiceImpl.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			
			int length =fileName.length();
			String typefile=fileName.substring(length-3,length);
			if (typefile.equals("png")||typefile.equals("peg")||typefile.equals("jpg"))
			{
				pub.setImage(fileDownloadUri);
			}
			else
			{
				pub.setVideo(fileDownloadUri);
			}
				
			return publiciteRepository.save(pub);

	}
}
