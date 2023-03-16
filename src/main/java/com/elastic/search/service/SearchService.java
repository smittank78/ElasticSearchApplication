package com.elastic.search.service;

import java.util.Arrays;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.elastic.search.dto.Student;

@Service
public class SearchService 
{
	@Autowired
	private ElasticsearchOperations operations; 

	/*
	 * create criteria
	 * create query
	 * operations.search()
	 */
	public List<SearchHit<Student>> findByNameByCriteria(String name)
	{
		Criteria criteria = new Criteria("description").contains(name);
		Query query = new CriteriaQuery(criteria);
		SearchHits<Student> hits = operations.search(query, Student.class,IndexCoordinates.of("student"));
		List<SearchHit<Student>> list = hits.getSearchHits().stream().toList();	
		return list;
	}
	/*
	 * create QueryBuilder
	 * create query using native query builder
	 * operations.search()
	 */
	public List<SearchHit<Student>> findByNameByNative(String name)
	{
		QueryBuilder builders = QueryBuilders.matchQuery("name", name);
		Query query = new NativeSearchQueryBuilder().withQuery(builders).withIds(Arrays.asList(1,2,3,4).toString()).build();
		SearchHits<Student> hits = operations.search(query, Student.class,IndexCoordinates.of("student"));
		List<SearchHit<Student>> list = hits.getSearchHits().stream().toList();	
		return list;
	}
}