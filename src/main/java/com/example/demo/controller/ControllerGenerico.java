package com.example.demo.controller;



import com.example.demo.entities.BaseEntity;
import com.example.demo.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.BaseService;

import java.util.Map;


public class ControllerGenerico <E extends BaseEntity, S extends BaseServiceImpl<E, Integer>>{
	
	@Autowired	
	protected S service;

	@GetMapping("/allPaged")
	public ResponseEntity<Map<String, Object>> getAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(service.findAll(page - 1, size));
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/all")
	@Transactional
	public ResponseEntity<?> getAll(){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body
					("{\"Mi mensaje get todos\": \""+e.getMessage()+"\"}");
			
		}
		
	}

	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<?>  getOne(@PathVariable int id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body
					("{\"Mi mensaje get uno\": \""+e.getMessage()+"\"}");
			
		}
		
	}	
	
	@PostMapping("/")
	@Transactional
	public ResponseEntity<?> post(@RequestBody E entityForm) {
		
		try {
		

			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entityForm));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
					("{\"Mi mensaje post\": \""+e.getMessage()+"\"}");
						
		}
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> put(@PathVariable int id, @RequestBody E entityForm) {
		
		try {
			
			
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entityForm));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
					("{\"Mi mensaje put\": \""+e.getMessage()+"\"}");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable int id) {
	
		try {
			
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. Por favor chequee el ID o el BODY request, y vuelva a intentar más tarde.\"}");
			
		}
		
	}

}