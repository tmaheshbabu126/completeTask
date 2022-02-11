package com.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import antlr.collections.List;

@Entity
public class SearchKeyWords {
	

		
		public SearchKeyWords() {
		super();
	}
		public SearchKeyWords(Long id, String keyword, int count) {
		super();
		this.id = id;
		this.keyword = keyword;
		this.count = count; 
	}
		@Id
	 	@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	 
		/*
		 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "searchKeyWords") private
		 * List<SearchDate> searchDate;
		 */
		
		private String keyword;
	 	private int count;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		@Override
		public String toString() {
			return "SearchKeyWords [id=" + id + ", keyword=" + keyword + ", count=" + count + "]";
		}
		
	 	
}
