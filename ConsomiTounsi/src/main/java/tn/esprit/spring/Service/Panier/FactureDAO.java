package tn.esprit.spring.Service.Panier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.FactureRepository;

@Service
public class FactureDAO implements IFacture {
@Autowired
FactureRepository factureRepository;
@Autowired
CommandeRepository commandeRepository;
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
public boolean CreePdf(List<lignecommandeproduit>commandes ,ServletContext context,HttpServletRequest request ,HttpServletResponse reponse ) throws MalformedURLException, IOException
{
	List<Commande>cs=commandeRepository.findAll();
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
	//Font mainFont = FontFactory.getFont("Arial", 50, BaseColor.BLACK);
    Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);

    Paragraph paragraph = new Paragraph("FACTURE", boldFont);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    paragraph.setIndentationLeft(50);
    paragraph.setIndentationRight(50);
    paragraph.setSpacingAfter(20);
    d.add(Image.getInstance("C:\\Users\\Iheb\\Pictures\\Saved Pictures\\iii.jpg"));
    d.add(paragraph);


    PdfPTable table = new PdfPTable(4);//column amount
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);
    table.setSpacingAfter(10);

    Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

    float[] columnWidths = {2f, 2f, 2f, 2f};
    table.setWidths(columnWidths);
    PdfPCell name = new PdfPCell(new Paragraph("Nom de produit", tableHeader));
    name.setBorderColor(BaseColor.BLACK);
    name.setPaddingLeft(10);
    name.setHorizontalAlignment(Element.ALIGN_CENTER);
    name.setVerticalAlignment(Element.ALIGN_CENTER);
    name.setBackgroundColor(BaseColor.DARK_GRAY);
    name.setExtraParagraphSpace(5f);
    table.addCell(name);

    PdfPCell email = new PdfPCell(new Paragraph("Prix unitaire", tableHeader));
    email.setBorderColor(BaseColor.BLACK);
    email.setPaddingLeft(10);
    email.setHorizontalAlignment(Element.ALIGN_CENTER);
    email.setVerticalAlignment(Element.ALIGN_CENTER);
    email.setBackgroundColor(BaseColor.DARK_GRAY);
    email.setExtraParagraphSpace(5f);
    table.addCell(email);

    PdfPCell mobile = new PdfPCell(new Paragraph("Quantité", tableHeader));
    mobile.setBorderColor(BaseColor.BLACK);
    mobile.setPaddingLeft(10);
    mobile.setHorizontalAlignment(Element.ALIGN_CENTER);
    mobile.setVerticalAlignment(Element.ALIGN_CENTER);
    mobile.setBackgroundColor(BaseColor.DARK_GRAY);
    mobile.setExtraParagraphSpace(5f);
    table.addCell(mobile);

    PdfPCell address = new PdfPCell(new Paragraph("Total", tableHeader));
    address.setBorderColor(BaseColor.BLACK);
    address.setPaddingLeft(10);
    address.setHorizontalAlignment(Element.ALIGN_CENTER);
    address.setVerticalAlignment(Element.ALIGN_CENTER);
    address.setBackgroundColor(BaseColor.DARK_GRAY);
    address.setExtraParagraphSpace(5f);
    table.addCell(address);
	
	boolean firstTime = true;
	double sum=0;
	for(lignecommandeproduit c : commandes)
	{
		
	            //sum += c.getTotal();
	        
	      

		
		if(firstTime){
		
			
			Phrase PH = new Phrase(                 "                                                                           Nom:  "+c.getName()+"\n\n                                                                            DateCommande: "+c.getDate());
		    Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		
			PH.setFont(boldFont1);
			
			d.add(PH);  
			firstTime = false;
			
		}
		
		
		
		 PdfPCell nameValue = new PdfPCell(new Paragraph(c.getNomProduit(), tableHeader));
         nameValue.setBorderColor(BaseColor.BLACK);
         nameValue.setPaddingLeft(10);
         nameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
         nameValue.setVerticalAlignment(Element.ALIGN_CENTER);
         nameValue.setBackgroundColor(BaseColor.WHITE);
         nameValue.setExtraParagraphSpace(5f);
         table.addCell(nameValue);

         PdfPCell emailValue = new PdfPCell(new Paragraph(String.valueOf(c.getPrice()), tableHeader));
         emailValue.setBorderColor(BaseColor.BLACK);
         emailValue.setPaddingLeft(10);
         emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
         emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
         emailValue.setBackgroundColor(BaseColor.WHITE);
         emailValue.setExtraParagraphSpace(5f);
         table.addCell(emailValue);

         PdfPCell mobileValue = new PdfPCell(new Paragraph(String.valueOf(c.getQuantity()), tableHeader));
         mobileValue.setBorderColor(BaseColor.BLACK);
         mobileValue.setPaddingLeft(10);
         mobileValue.setHorizontalAlignment(Element.ALIGN_CENTER);
         mobileValue.setVerticalAlignment(Element.ALIGN_CENTER);
         mobileValue.setBackgroundColor(BaseColor.WHITE);
         mobileValue.setExtraParagraphSpace(5f);
         table.addCell(mobileValue);

         PdfPCell addressValue = new PdfPCell(new Paragraph(String.valueOf(c.getTotal()), tableHeader));
         addressValue.setBorderColor(BaseColor.BLACK);
         addressValue.setPaddingLeft(10);
         addressValue.setHorizontalAlignment(Element.ALIGN_CENTER);
         addressValue.setVerticalAlignment(Element.ALIGN_CENTER);
         addressValue.setBackgroundColor(BaseColor.WHITE);
         addressValue.setExtraParagraphSpace(5f);
         table.addCell(addressValue);
   	
	}

	  d.add(table);
	boolean firstTime2 = true;
    for(Commande c1: cs)
			if(firstTime2){
				if(c1.getPourcentageDeRemise()==0)
				{
					 Phrase PH3 = new Phrase("\nMontant "+	c1.getMontant());
					 Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
						PH3.setFont(boldFont1);
						d.add(PH3);
					 
				}
				else
				{
				 Phrase PH2 = new Phrase("\nMontant "+	c1.getMontant()+"\nPourcentage"+30+ "%\nMontant apres remise "+(c1.getPourcentageDeRemise()));
				//Font mainFont2 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
				 Font boldFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
				PH2.setFont(boldFont1);
				
				d.add(PH2);
				firstTime2 = false;
				}
			}
	
	 
	 
	  d.close();
      writer.close();
	 
      return true;
} catch (FileNotFoundException | DocumentException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

	
	
	return true;
}

}