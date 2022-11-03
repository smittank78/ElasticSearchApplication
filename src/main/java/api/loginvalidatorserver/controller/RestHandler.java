package api.loginvalidatorserver.controller;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.loginvalidatorserver.entity.Customer;
import api.loginvalidatorserver.repo.ElasticSearchCustomerRepo;
import api.loginvalidatorserver.service.ElasticSearchService;

@RestController
@RequestMapping("/customer")
@EnableElasticsearchRepositories
public class RestHandler
{
	@Autowired
	private ElasticSearchCustomerRepo repository;
	
	@Autowired
	private ElasticSearchService service;
	
	@GetMapping("/")
	public String test() 
	{
		return "test done";
	}
	@GetMapping("/insert")
	public String insertCustomer(@RequestBody Customer customer) 
	{
		Customer c = repository.save(customer);
		return c.getName();
	}
	@GetMapping("/{name}")
	public Iterable<Customer> getAllCustomer(@PathParam("name") String name) 
	{
		return repository.findByName(name);
	}
	@GetMapping("/like/{name}")
	public Iterable<Customer> getRelatedCustomer(@PathParam("name") String name) 
	{
		return service.getRelatedCustomer(name);
	}
	@GetMapping("/all")
	public Iterable<Customer> getAllCustomer() 
	{
		return repository.findAll();
	}
	@GetMapping("/delete")
	public Iterable<Customer> deleteCustomer(@PathParam("name") String name) 
	{
		return repository.deleteByName(name);
	}
}