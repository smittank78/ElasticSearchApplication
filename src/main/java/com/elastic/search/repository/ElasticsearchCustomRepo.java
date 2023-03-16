package com.elastic.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.elastic.search.dto.Student;

@Repository
public interface ElasticsearchCustomRepo extends ElasticsearchRepository<Student,Integer>{
	
	List<Student> findByName(String name);
}