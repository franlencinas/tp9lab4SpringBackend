package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.repository.BaseRepository;
import com.example.demo.specifications.SearchSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Instrumento;
import com.example.demo.repository.InstrumentoRepository;

@Service
public class InstrumentoService extends BaseServiceImpl<Instrumento, Integer> {

    private String upload_folder = ".//src//main//resources//images//";

    public InstrumentoService(BaseRepository<Instrumento, Integer> baseRepository) {
        super(baseRepository);
    }

    public void saveFile(MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_folder + file.getOriginalFilename());
            Files.write(path,bytes);
        }
    }

    public List<Instrumento> filterAll(String filter) throws Exception {
        try {
            SearchSpecification<Instrumento> spec = new SearchSpecification<Instrumento>();
            Specification<Instrumento> filterName = spec.findByProperty("instrumento", filter);

            List<Instrumento> entities = baseRepository.findAll(Specification.where(filterName));

            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }




}