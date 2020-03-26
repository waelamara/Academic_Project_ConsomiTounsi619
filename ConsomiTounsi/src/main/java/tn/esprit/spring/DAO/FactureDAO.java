package tn.esprit.spring.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Repository.FactureRepository;

@Service
public class FactureDAO {
@Autowired
FactureRepository factureRepository;
public Facture findOne(Long id) {
	return factureRepository.getOne(id);
}
public List<Facture> findAll() {
	return factureRepository.findAll();
}


public Facture  save ( Facture f)
{
	f.setDate(LocalDate.now());
	return factureRepository.save(f);
}
public void Delete(Facture f) {
	factureRepository.delete(f);
}
public List<lignecommandeproduit> FactureParIdUser( long id) {
	return factureRepository.FactureParIdUser(id);

}
public boolean CreePdf(List<lignecommandeproduit>commandes ,ServletContext context,HttpServletRequest request ,HttpServletResponse reponse )
{
	try {
	Document d = new Document(PageSize.A4,15,15,45,30);
	
String filePath = context.getRealPath("/resources/report");
File file = new File(filePath);
boolean exists =new File(filePath).exists();
if (!exists)
{
	new File(filePath).mkdirs();
	
}

PdfWriter writer =PdfWriter.getInstance(d, new FileOutputStream(file+"/"+"employees"+".pdf"));
	d.open();
	
	//PdfPTable table = new PdfPTable(4);
   // Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
	for(lignecommandeproduit c : commandes)
	{
		
		Phrase C = new Phrase("\n "+"nom de Produit:    "+c.getNomProduit()+"   "+"quantity:    "+c.getQuantity()+"     prix:   "+c.getPrice()+"     total:   "+c.getTotal());
		
		//Phrase C1 = new Phrase("nom:      "+c.getClient().getNomClient()+"\n");
	
		d.add(C);
		//d.add(C1);
		
	
		
		
	
	
		
		//d.add(nameValue);
	}
	//PdfPCell nameValue  = new PdfPCell (new Paragraph(c.getStatus()));
	
	
	  d.close();
      writer.close();
	  //outputStream.flush();
      //outputStream.close();
      return true;
} catch (FileNotFoundException | DocumentException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	
	
	return true;
}

}