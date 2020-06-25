package com.example.demo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.example.demo.entities.BaseEntity;

public interface BaseService<E extends BaseEntity, ID extends Serializable> {

	public Map<String, Object> findAll(int page, int size) throws Exception;

	public List<E> findAll() throws Exception ;
	
	public E findById(ID id) throws Exception;
	
	public E save(E entityForm) throws Exception;
	
	public E update(ID id, E entityForm) throws Exception;
	
	public boolean delete(ID id) throws Exception;
	
}
