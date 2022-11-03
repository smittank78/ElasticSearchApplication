package api.loginvalidatorserver.service;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import api.loginvalidatorserver.entity.Customer;
import api.loginvalidatorserver.repo.ElasticSearchCustomerRepo;

@Service
public class ElasticSearchService
{
	@Autowired
	private ElasticSearchCustomerRepo customerRepo;
	
	@Autowired
	ElasticsearchOperations operations;
	
	
	/*
	 * Using Criteria
	 * create criteria
	 * create query
	 * search using ElasticSearchOpertaion.search(query,entity.class,indexName)
	 */
	public List<Customer> getRelatedCustomer(@PathParam("name") String name) 
	{
		Criteria criteria = new Criteria("name").contains(name).and("phone").contains("9").and("phone").contains("1");
		Query query = new CriteriaQuery(criteria);
		SearchHits<Customer> search = operations.search(query, Customer.class,IndexCoordinates.of("customer_index"));
		List<Customer> listOfCustomer = new ArrayList<>();
		search.forEach(a->{listOfCustomer.add(a.getContent());});
		return listOfCustomer;
	}
	/*
	 * Using Criteria
	 * create criteria
	 * create query
	 * search using ElasticSearchOpertaion.search(query,entity.class,indexName)
	 */
	public List<Customer> getByPhone(@PathParam("name") String name) 
	{
		QueryBuilder queryBuilder = (QueryBuilder) QueryBuilders.matchQuery("name", name);

	    Query query = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		SearchHits<Customer> search = operations.search(query, Customer.class,IndexCoordinates.of("customer_index"));
		List<Customer> listOfCustomer = new ArrayList<>();
		search.forEach(a->{listOfCustomer.add(a.getContent());});
		return listOfCustomer;
	}
	public List<Customer> findByProductName(final String productName)
	{
		String query = "{\"match\":{\"name\":{\"query\":\""+ productName + "\"}}}\"";
	    Query searchQuery = new StringQuery(query);	    
	    SearchHits<Customer> search = operations.search(searchQuery,Customer.class,IndexCoordinates.of("customer_index"));
	    List<Customer> listOfCustomer = new ArrayList<>();
		search.forEach(a->{listOfCustomer.add(a.getContent());});
		return listOfCustomer;
	}
}