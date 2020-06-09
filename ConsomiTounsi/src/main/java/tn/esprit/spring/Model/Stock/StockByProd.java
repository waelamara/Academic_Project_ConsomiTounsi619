package tn.esprit.spring.Model.Stock;

public class StockByProd {
	
	private Long n;
	private String nomproduit;
	public Long getN() {
		return n;
	}
	public void setN(Long n) {
		this.n = n;
	}
	public String getNomproduit() {
		return nomproduit;
	}
	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}
	public StockByProd() {
		super();
	}
	public StockByProd(Long n, String nomproduit) {
		super();
		this.n = n;
		this.nomproduit = nomproduit;
	}
	public StockByProd(Long n) {
		super();
		this.n = n;
	}
	
	
	

}
