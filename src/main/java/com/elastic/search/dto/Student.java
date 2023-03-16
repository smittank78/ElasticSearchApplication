package com.elastic.search.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "student",replicas = 2,shards = 2)
public class Student {

	public Student() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@Field(type = FieldType.Integer)
	private Integer id;
	@Field(type = FieldType.Text)
	private String name;
	@Field(type = FieldType.Double)
	private Double phone;
	@Field(type = FieldType.Text)
	private String clg;
	@Field(type = FieldType.Short)
	private Short sem;
	@Field(type = FieldType.Text)
	private String tracer;
	@Field(type = FieldType.Text)
	private String span;
	@Field(type = FieldType.Text)
	private String description;
	
	public String getTracer() {
		return tracer;
	}
	public void setTracer(String tracer) {
		this.tracer = tracer;
	}
	public String getSpan() {
		return span;
	}
	public void setSpan(String span) {
		this.span = span;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPhone() {
		return phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPhone(Double phone) {
		this.phone = phone;
	}
	public String getClg() {
		return clg;
	}
	public void setClg(String clg) {
		this.clg = clg;
	}
	public Short getSem() {
		return sem;
	}
	public void setSem(Short sem) {
		this.sem = sem;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phone=" + phone + ", clg=" + clg + ", sem=" + sem
				+ ", tracer=" + tracer + ", span=" + span + ", description=" + description + "]";
	}
}
