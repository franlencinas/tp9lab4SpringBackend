package com.example.demo.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entities.BaseEntity;
import com.example.demo.entities.Instrumento;
import com.example.demo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<E extends BaseEntity, ID extends Serializable>
		implements BaseService<E, ID> {

	protected BaseRepository<E, ID> baseRepository;

	@Autowired
	public BaseServiceImpl(BaseRepository<E,ID> baseRepository){
		this.baseRepository = baseRepository;
	}

	@Override
	public Map<String, Object> findAll(int page, int size) throws Exception {
		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<E> entityPage = baseRepository.findAll(pageable);
			List<E> entities = entityPage.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("payload", entities);
			response.put("length", entityPage.getTotalElements());

			return response;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<E> findAll() throws Exception {
		try {
			List<E> entities = baseRepository.findAll();
			return entities;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
	
	}

	@Override
	public E findById(ID id)throws Exception{
		try {
			Optional<E> varOptional = baseRepository.findById(id);
			
			E entity = varOptional.get();
			
			return entity;
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
			
		}
	}

	@Override
	public E save(E entityForm) throws Exception {
		try {

			entityForm = baseRepository.save(entityForm);

			return entityForm;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
	}

	@Override
	public E update(ID id, E entityForm) throws Exception {

		try {
			Optional<E> entityOptional = baseRepository.findById(id);

			E entity = entityOptional.get();

			entity = baseRepository.save(entityForm);

			return entity;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

	}
	
	@Override
	public boolean delete(ID id) throws Exception {
		
		try {
			
			if (baseRepository.existsById(id)) {

				baseRepository.deleteById(id);
				return true;
				
			} else {

				throw new Exception();
				
			}
		} catch (Exception e) {

			throw new Exception(e.getMessage());
			
		}
	}
}
