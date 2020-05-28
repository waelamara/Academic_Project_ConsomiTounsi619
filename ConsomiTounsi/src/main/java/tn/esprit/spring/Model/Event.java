package tn.esprit.spring.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Event  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long title;
	
	private LocalDate start;
	
	private Date end ;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTitle() {
		return title;
	}

	public void setTitle(long title) {
		this.title = title;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Event(long id, long title, LocalDate start, Date end) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
	}
	public Event(long id, long title, LocalDate start) {
		super();
		this.id = id;
		this.title = title;
		this.start = start;
		
	}

	public Event() {
		super();
	}
	
	
	
	

}
