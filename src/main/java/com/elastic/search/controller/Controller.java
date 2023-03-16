package com.elastic.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.search.dto.Student;
import com.elastic.search.repository.ElasticsearchCustomRepo;
import com.elastic.search.service.SearchService;

@RestController
public class Controller 
{
	@Autowired
	private ElasticsearchCustomRepo repository;
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	private Tracer tracerId;
	
	/*
	 * save student in elastic search
	 */
	@PostMapping("/create")
	private Student name(@RequestBody Student student) 
	{
		/*
		 * providing tracerId and spanID
		 */
		student.setTracer(this.tracerId.currentSpan().context().traceId());
		student.setSpan(this.tracerId.currentSpan().context().spanId());
		return repository.save(student);
	}
	
	@GetMapping("/findAll")
	/*
	 * find all students
	 */
	private List<Student> name() 
	{
		Iterable<Student> findAll = repository.findAll();
		List<Student> list = new ArrayList<>();
		findAll.forEach(student -> list.add(student));
		return list;
	}
	/*
	 * find student using repository
	 */
	@GetMapping("/find/{name}")
	private List<Student> name(@PathVariable String name) 
	{
		return repository.findByName(name);
	}
	/*
	 * find student using word
	 */
	@GetMapping("/find/description/{description}")
	private List<SearchHit<Student>> description(@PathVariable String description) 
	{
		return searchService.findByNameByCriteria(description);
	}
	/*
	 * find student using name within id 1 , 2 , 3 and 4
	 */
	@GetMapping("/find/contain/{name}")
	private List<SearchHit<Student>> natively(@PathVariable String name) 
	{
		return searchService.findByNameByNative(name);
	}
}