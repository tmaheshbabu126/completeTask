package com.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SearchDate {

	
	  public SearchDate() {
		super();
	}

	public SearchDate(long i, Date sd, int id) {
		super();
		this.did = i;
		this.sdate = sd;
		this.searchKeyWords_id = id;
	}

	@Id
	  
	  @GeneratedValue(strategy = GenerationType.AUTO) private Long did;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date sdate;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY) private int searchKeyWords;
	 */
	
	//@ManyToOne(fetch = FetchType.LAZY)
	private int searchKeyWords_id;

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public int getSearchKeyWords() {
		return searchKeyWords_id;
	}

	public void setSearchKeyWords(int searchKeyWords) {
		this.searchKeyWords_id = searchKeyWords;
	}
	
	
	
}
