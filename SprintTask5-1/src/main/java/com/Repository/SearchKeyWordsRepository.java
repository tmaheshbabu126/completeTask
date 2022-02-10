package com.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Entity.SearchKeyWords;







@Repository
public interface SearchKeyWordsRepository extends JpaRepository<SearchKeyWords,Long> {

	SearchKeyWords findByKeyword(String q);
	@Query(value ="select id from search_key_words where keyword = ?1", nativeQuery=true)
	   public int findIdByKeyword(@Param("keyword") String keyword); 
	
	@Query(value ="select t1.id, count(*) dCount,t1.keyword from search_key_words as t1\r\n"
			+ "inner join search_date as t2 on t1.id=t2.search_key_words_id  \r\n"
			+ "where CAST(sdate AS DATE) = ?1 group by t1.id ", nativeQuery=true)
	public SearchKeyWords getByDate(@Param("dateSearch") Date dateSearch);
	

	
		
		
}
