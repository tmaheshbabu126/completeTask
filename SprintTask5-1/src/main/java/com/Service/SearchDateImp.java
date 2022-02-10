package com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.SearchDate;
import com.Repository.SearchDateRepository;

@Service
public class SearchDateImp implements SearchDateService{
	
	 @Autowired(required=true)
	    private SearchDateRepository sRepo;

	@Override
	public void addDate(SearchDate searchDate) {
		 sRepo.save(searchDate);
		System.out.println("the date search data is saved in database succesfully ");
		System.out.println(searchDate);
	} 

}
