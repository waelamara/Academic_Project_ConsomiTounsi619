package tn.esprit.spring.Controller.Panier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Panier.FactureImpl;
@Controller
public class FactureController {

	@Autowired
	FactureImpl factureDAO;
	
	@Autowired
	CommandeImpl commandeDao;
	
	@Autowired
	ServletContext context;
	
	
	public Facture AjouterFacture( Long idCommande, Facture f) 
	
	{
		Commande c =  commandeDao.findOne( idCommande);
		f.setCommande(c);
		return	factureDAO.save(f);
	}
	
	
	public List<Facture > getAllFacture(){
		
		return factureDAO.findAll();
		
	}
	
	
	public void DeleteProduit( Long idFacture) {
		Facture f = factureDAO.findOne(idFacture);
		factureDAO.Delete(f);
	}
	
	
	public List<lignecommandeproduit> panierParIdclient(long id) {
		
		return factureDAO.FactureParIdUser(id);
	}
	
	
	public void createpdf(HttpServletRequest request ,HttpServletResponse reponse,@PathVariable(value = "idUser") long id) throws MalformedURLException, IOException 
	{
	List<lignecommandeproduit> commandes =factureDAO.FactureParIdUser(id);
	boolean flag = factureDAO.CreePdf(commandes, context, request, reponse);
	if(flag)
	{
		String fullpath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
		fileDownload(fullpath,reponse,"employees.pdf");
	
	}
	}
	private void fileDownload(String fullpath,HttpServletResponse reponse,String fileName)
	{
		File f = new File(fullpath);
		final int buffer = 4096;
		
		if(f.exists())
		{
		try{
			FileInputStream inputStream = new FileInputStream(f);
			String mineType = context.getMimeType(fullpath);
			reponse.setContentType(mineType);
			//reponse.setHeader(""content-disposition:inline;", "filename="+fileName);"
					reponse.setHeader("content-disposition:inline; ", "filename="+ fileName);
			OutputStream outputStream = reponse.getOutputStream();
			byte[] buffers = new byte[buffer];
			int bytesRead = -1;
			while((bytesRead=inputStream.read(buffers))!=-1)
			{
				outputStream.write( buffers,0,bytesRead );
			}
			inputStream.close();
			outputStream.close();
			f.delete();
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		}
				
	}

}
