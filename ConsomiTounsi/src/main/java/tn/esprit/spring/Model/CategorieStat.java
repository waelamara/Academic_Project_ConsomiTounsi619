package tn.esprit.spring.Model;

public class CategorieStat {
	private String name;
	
	private long amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public CategorieStat( long amount,String name) {
		super();
		this.amount = amount;
		this.name = name;
		
	}

	public CategorieStat() {
		super();
	}
	
	

}
