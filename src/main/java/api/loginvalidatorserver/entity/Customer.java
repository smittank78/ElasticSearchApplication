package api.loginvalidatorserver.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import lombok.Data;

@Document(indexName = "customer_index",indexStoreType = "customer",shards = 2)
@Data
public class Customer 
{
	@Id
	@Field(name = "id")
	private int id;

	@Field(name = "name")
	private String name;	
	
	@Field(name = "phone")
	private int phone;
}