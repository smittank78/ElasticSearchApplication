package api.loginvalidatorserver.repo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import api.loginvalidatorserver.entity.Customer;

@Repository
public interface ElasticSearchCustomerRepo extends ElasticsearchRepository<Customer, Integer> 
{
	Iterable<Customer> findByName(String name);

	Iterable<Customer> deleteByName(String name);
}