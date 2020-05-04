package tn.esprit.spring.Service.Panier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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
import tn.esprit.spring.Repository.LigneCommandeRepository;

@Service
public class FactureImpl implements IFacture {

@Autowired
FactureRepository factureRepository;

@Autowired
CommandeRepository commandeRepository;

@Autowired
LigneCommandeRepository ligneCommandeRepository;


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

@Transactional
public void facturepdf (long id_facture){
	try {
		Facture f=factureRepository.getOne((long) id_facture);
		//List<lignecommandeproduit>commandes = panierRepository.panier_confirmer_ParIdclient(f.getCommande().getClient().getUserId());
		//System.out.println("/////////"+commandes);
	//	List<Commande>commandes=commandeRepository.findAll();
		List<lignecommandeproduit>commandes=ligneCommandeRepository.panierParIdclient(f.getCommande().getIdUser().getId());
	String file_name="C:\\Users\\Iheb\\Desktop\\Nouveau dossier\\my_facture"+f.getId()+".pdf";
	Document document=new Document(PageSize.A4,15,15,45,30);
		PdfWriter.getInstance(document, new FileOutputStream(file_name));
 document.open();
 ////////////////
 Image img=Image.getInstance("C:\\Users\\Iheb\\Desktop\\Nouveau dossier\\logo.png");
 img.setAlignment(Element.ALIGN_CENTER);
 img.setIndentationLeft(10);
 img.setIndentationRight(10);
 img.setSpacingAfter(10);
 document.add(img); 
 document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
 ////////////////////////////
 Font font = FontFactory.getFont("Cooper Black", 15, BaseColor.BLUE);
 LocalDate aujourdhui = f.getDate();
 document.add(new Paragraph("Destinataire :   "));
 SimpleDateFormat formater = null;
 formater = new SimpleDateFormat("dd-MM-yy");
 Paragraph nomClient=new Paragraph(" "+f.getCommande().getIdUser().getFirstName()+" "+f.getCommande().getIdUser().getLastName(),font);
 document.add(nomClient);
 Paragraph adresse=new Paragraph("Adresse : "+f.getCommande().getIdUser().getAddress()+"                                                                                              Date de facturation : "+f.getDate());
 document.add(adresse);
 document.add(new Paragraph("Code Postal : 8080                                                                                                  Echéance : "+f.getCommande().getDate()));
 Font mainFont = FontFactory.getFont("Cooper Black",35, BaseColor.BLACK);
 Paragraph para=new Paragraph("FACTURE N° "+f.getId(),mainFont);
 para.setAlignment(Element.ALIGN_CENTER);
 para.setIndentationLeft(10);
 para.setIndentationRight(10);
 para.setSpacingAfter(10);
 document.add(para);
 document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
 document.add(new Paragraph(" "));
 document.add(new Paragraph(" "));
 ///////////////////
 PdfPTable table = new PdfPTable(4);
 PdfPTable table2 = new PdfPTable(3);
  Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
 PdfPCell name = new PdfPCell(new Paragraph("Quantite", tableHeader));
    name.setBorderColor(BaseColor.BLACK);
    name.setPaddingLeft(10);
    name.setHorizontalAlignment(Element.ALIGN_CENTER);
    name.setVerticalAlignment(Element.ALIGN_CENTER);
    name.setBackgroundColor(BaseColor.LIGHT_GRAY);
    name.setExtraParagraphSpace(5f);
    table.addCell(name);
    PdfPCell nameProduit = new PdfPCell(new Paragraph("Produit", tableHeader));
    nameProduit.setBorderColor(BaseColor.BLACK);
    nameProduit.setPaddingLeft(10);
    nameProduit.setHorizontalAlignment(Element.ALIGN_CENTER);
    nameProduit.setVerticalAlignment(Element.ALIGN_CENTER);
    nameProduit.setBackgroundColor(BaseColor.LIGHT_GRAY);
    nameProduit.setExtraParagraphSpace(5f);
    table.addCell(nameProduit);
    PdfPCell Prix = new PdfPCell(new Paragraph("Prix", tableHeader));
    Prix.setBorderColor(BaseColor.BLACK);
    Prix.setPaddingLeft(10);
    Prix.setHorizontalAlignment(Element.ALIGN_CENTER);
    Prix.setVerticalAlignment(Element.ALIGN_CENTER);
    Prix.setBackgroundColor(BaseColor.LIGHT_GRAY);
    Prix.setExtraParagraphSpace(5f);
    table.addCell(Prix);
    PdfPCell Totale = new PdfPCell(new Paragraph("Totale", tableHeader));
    Totale.setBorderColor(BaseColor.BLACK);
    Totale.setPaddingLeft(10);
    Totale.setHorizontalAlignment(Element.ALIGN_CENTER);
    Totale.setVerticalAlignment(Element.ALIGN_CENTER);
    Totale.setBackgroundColor(BaseColor.LIGHT_GRAY);
    Totale.setExtraParagraphSpace(5f);
    table.addCell(Totale);
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    ////////////////////////////
    for(lignecommandeproduit c : commandes)
	{
    	 PdfPCell quantiteval = new PdfPCell(new Paragraph(String.valueOf(c.getQuantity()), tableHeader));
 	    quantiteval.setBorderColor(BaseColor.BLACK);
 	    quantiteval.setPaddingLeft(10);
 	    quantiteval.setHorizontalAlignment(Element.ALIGN_CENTER);
 	    quantiteval.setVerticalAlignment(Element.ALIGN_CENTER);
 	    quantiteval.setBackgroundColor(BaseColor.WHITE);
 	    quantiteval.setExtraParagraphSpace(5f);
 	    table.addCell(quantiteval);
 	    PdfPCell produiteval = new PdfPCell(new Paragraph(c.getNomProduit(), tableHeader));
 	    produiteval.setBorderColor(BaseColor.BLACK);
 	    produiteval.setPaddingLeft(10);
 	    produiteval.setHorizontalAlignment(Element.ALIGN_CENTER);
 	    produiteval.setVerticalAlignment(Element.ALIGN_CENTER);
 	    produiteval.setBackgroundColor(BaseColor.WHITE);
 	    produiteval.setExtraParagraphSpace(5f);
 	    table.addCell(produiteval);
 	    PdfPCell prixval = new PdfPCell(new Paragraph(String.valueOf(c.getPrice()), tableHeader));
 	    prixval.setBorderColor(BaseColor.BLACK);
 	    prixval.setPaddingLeft(10);
 	    prixval.setHorizontalAlignment(Element.ALIGN_CENTER);
 	    prixval.setVerticalAlignment(Element.ALIGN_CENTER);
 	    prixval.setBackgroundColor(BaseColor.WHITE);
 	    prixval.setExtraParagraphSpace(5f);
 	    table.addCell(prixval);
 	    PdfPCell Totaleval = new PdfPCell(new Paragraph(String.valueOf(c.getTotal()), tableHeader));
 	    Totaleval.setBorderColor(BaseColor.BLACK);
 	    Totaleval.setPaddingLeft(10);
 	    Totaleval.setHorizontalAlignment(Element.ALIGN_CENTER);
 	    Totaleval.setVerticalAlignment(Element.ALIGN_CENTER);
 	    Totaleval.setBackgroundColor(BaseColor.WHITE);
 	    Totaleval.setExtraParagraphSpace(5f);
 	    table.addCell(Totaleval);
	}
    /////////////////////////////////
    PdfPCell PrixTotale = new PdfPCell(new Paragraph("Prix_Totale", tableHeader));
    PrixTotale.setBorderColor(BaseColor.BLACK);
    PrixTotale.setPaddingLeft(10);
    PrixTotale.setHorizontalAlignment(Element.ALIGN_CENTER);
    PrixTotale.setVerticalAlignment(Element.ALIGN_CENTER);
    PrixTotale.setBackgroundColor(BaseColor.LIGHT_GRAY);
    PrixTotale.setExtraParagraphSpace(5f);
    table2.addCell(PrixTotale);
    PdfPCell Pourcentage = new PdfPCell(new Paragraph("Reduction", tableHeader));
    Pourcentage.setBorderColor(BaseColor.BLACK);
    Pourcentage.setPaddingLeft(10);
    Pourcentage.setHorizontalAlignment(Element.ALIGN_CENTER);
    Pourcentage.setVerticalAlignment(Element.ALIGN_CENTER);
    Pourcentage.setBackgroundColor(BaseColor.LIGHT_GRAY);
    Pourcentage.setExtraParagraphSpace(5f);
    table2.addCell(Pourcentage);
    PdfPCell Prixfinal = new PdfPCell(new Paragraph("Prix_finale", tableHeader));
    Prixfinal.setBorderColor(BaseColor.BLACK);
    Prixfinal.setPaddingLeft(10);
    Prixfinal.setHorizontalAlignment(Element.ALIGN_CENTER);
    Prixfinal.setVerticalAlignment(Element.ALIGN_CENTER);
    Prixfinal.setBackgroundColor(BaseColor.LIGHT_GRAY);
    Prixfinal.setExtraParagraphSpace(5f);
    table2.addCell(Prixfinal);
    PdfPCell prix_totalval = new PdfPCell(new Paragraph(String.valueOf(f.getCommande().getMontant()), tableHeader));
    prix_totalval.setBorderColor(BaseColor.BLACK);
    prix_totalval.setPaddingLeft(10);
    prix_totalval.setHorizontalAlignment(Element.ALIGN_CENTER);
    prix_totalval.setVerticalAlignment(Element.ALIGN_CENTER);
    prix_totalval.setBackgroundColor(BaseColor.WHITE);
    prix_totalval.setExtraParagraphSpace(5f);
	    table2.addCell(prix_totalval);
	   PdfPCell Pourcentageval = new PdfPCell(new Paragraph("30%", tableHeader));
	  Pourcentageval.setBorderColor(BaseColor.BLACK);
	 Pourcentageval.setPaddingLeft(10);
	Pourcentageval.setHorizontalAlignment(Element.ALIGN_CENTER);
	Pourcentageval.setVerticalAlignment(Element.ALIGN_CENTER);
	Pourcentageval.setBackgroundColor(BaseColor.WHITE);
	Pourcentageval.setExtraParagraphSpace(5f);
    table2.addCell(Pourcentageval);
    PdfPCell prix_finalval = new PdfPCell(new Paragraph(String.valueOf(f.getCommande().getPourcentageDeRemise()), tableHeader));
    prix_finalval.setBorderColor(BaseColor.BLACK);
    prix_finalval.setPaddingLeft(10);
    prix_finalval.setHorizontalAlignment(Element.ALIGN_CENTER);
    prix_finalval.setVerticalAlignment(Element.ALIGN_CENTER);
    prix_finalval.setBackgroundColor(BaseColor.WHITE);
    prix_finalval.setExtraParagraphSpace(5f);
	    table2.addCell(prix_finalval);
    document.add(table);
    document.add(new Paragraph("  "));
    document.add(table2);
    document.add(new Paragraph("  "));
    document.add(new Paragraph("  "));
	 document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------------"));
	 document.add(new Paragraph("  "));
	    document.add(new Paragraph("  "));
	 document.add(new Paragraph("Telephone  :(+216) 72 755 885   "+"                                                             Adresse : Ariana Soghra /Tunis "));
	 document.add(new Paragraph("Fax          :(+216) 72 063 906   "+"                                                                                      Code Postal :2081  "));
	 document.add(new Paragraph("Email       :ConsommiToounsi.619@gmail.com  "));
 document.close();
	} catch (FileNotFoundException | DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}