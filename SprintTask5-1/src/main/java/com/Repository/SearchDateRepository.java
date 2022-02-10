package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.SearchDate;




@Repository
public interface SearchDateRepository extends JpaRepository<SearchDate,Long> {

}
