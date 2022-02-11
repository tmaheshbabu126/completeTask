package com.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.SearchKeyWords;
import com.Repository.SearchKeyWordsRepository;








@Service
public class SearchKeyWordsServiceImp implements SearchKeyWordsService{



	@Autowired(required=true)
	private SearchKeyWordsRepository sRepo;





	@Override
	public SearchKeyWords addSearch(SearchKeyWords searchKeyWords) {
		String key = searchKeyWords.getKeyword();
		SearchKeyWords t = null;




		t = sRepo.findByKeyword(key);
		//t1 = sRepo.findById(id).orElseThrow(s1);
		if(t==null) {
			/* SearchKeyWords searchKeyWords1= new SearchKeyWords(); */
			sRepo.save(searchKeyWords); // saving/adding tenant values in database
			System.out.println("saved in database");}
		else {
			try {
				updateSearch(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;





		//finding teanant by id if not found throwing user defined exception


	}

	@Override
	public SearchKeyWords updateSearch(SearchKeyWords t) throws Exception {
		// TODO Auto-generated method stub
		SearchKeyWords t1 = null;
		long id = t.getId();

		Supplier<Exception> s1 = ()->new Exception(" id is not present in the database");

		t1 = sRepo.findById(id).orElseThrow(s1); //finding teanant by id if not found throwing user defined exception
		t1.setKeyword(t.getKeyword());
		t1.setCount(t.getCount()+1);
		System.out.println(t1);
		sRepo.save(t1);//saving it in database
		System.out.println("updated in database");
		return t1;
	}

	@Override
	public List<SearchKeyWords> getAllSearch() {
		// TODO Auto-generated method stub
		List<SearchKeyWords> t1 = null;
		//	Supplier<Exception> s1 = ()->new Exception(" id is not present in the database");
		t1 = sRepo.findAll();

		return t1;
		//return null;
	}

	public int findByIdKey(String keyword) {

		int id = sRepo.findIdByKeyword(keyword);
		System.out.println("in function the id we got is "+id);
		return id;

	}

	public List<SearchKeyWords> findByDate(Date dateSearch) {
		List<SearchKeyWords> s = sRepo.getByDate(dateSearch);
		return s;

	}

















}
