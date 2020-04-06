package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


public class lignecommandeproduit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomProduit;
	private int quantity;
	private float price;
	private float total;
private String name ;
	private LocalDate date;
	private float montant;

	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	

	public double getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public lignecommandeproduit(int quantity, float price) {
		super();
		this.quantity = quantity;
		this.price = price;
	
	}
	public lignecommandeproduit() {
	
	}
	public lignecommandeproduit( String nomProduit,int quantity, float price,float total) {
		super();
		this.nomProduit = nomProduit;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		
	}
	

	public lignecommandeproduit(LocalDate date, float montant) {
		this.date = date;
		this.montant = montant;
	}
	public lignecommandeproduit( LocalDate date,String nomProduit,int quantity, float price,float total,String name,float montant) {
		super();
		this.date = date;
		this.nomProduit = nomProduit;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.name=name;
		this. montant = montant;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public lignecommandeproduit( int quantity,String nomProduit) {
		super();
		this.quantity = quantity;
		this.nomProduit = nomProduit;
		
	}
	
	
	
	

}
