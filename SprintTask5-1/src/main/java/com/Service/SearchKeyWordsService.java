package com.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.Entity.SearchDate;
import com.Entity.SearchKeyWords;

@Service
public interface SearchKeyWordsService {

	
	public SearchKeyWords addSearch(SearchKeyWords searchKeyWords) ;
	public SearchKeyWords updateSearch(SearchKeyWords searchKeyWords) throws Exception;
//	SearchKeyWords addSearch(String q);
	public List<SearchKeyWords> getAllSearch(); 
	
	



}
