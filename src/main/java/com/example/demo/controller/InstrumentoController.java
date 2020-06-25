package com.example.demo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Instrumento;
import com.example.demo.service.InstrumentoService;
import com.example.demo.service.UploadService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/instrumentos")
@Transactional
public class InstrumentoController extends ControllerGenerico<Instrumento, InstrumentoService>{
	@Autowired
    private UploadService uploadFileService;
	
	private static final String fileBasePath = ".//src//main//resources//images//";

	@GetMapping("/buscar")
    public ResponseEntity<?> getAllFiltered(
            @RequestParam(required = false) String filter) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.filterAll(filter));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/images")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<Object>("Seleccionar un archivo", HttpStatus.OK);
        }

        try {
            uploadFileService.saveFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \""+e.getMessage()+"\"}");
        }

        return new ResponseEntity<Object>("Archivo subido correctamente", HttpStatus.OK);
    }
    
    // Metodo que no define content type
    /*@GetMapping("/images/{fileName:.+}")
    public ResponseEntity<?> downloadFileFromLocal(@PathVariable String fileName) {
    	Path path = Paths.get(fileBasePath + fileName);
    	Resource resource = null;
    	try {
    		resource = new UrlResource(path.toUri());
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(resource);
    }*/
    
    
    // Metodo que define content type
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Cargar el archivo como Resource
        Resource resource;

        Path path = Paths.get(fileBasePath + fileName);
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        // Determinar el content type del archivo
        String contentType = null;
        try {
        	
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            
        } catch (IOException ex) {
            System.out.println("No se pudo determir el ContentType.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "image/jpeg";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
 
}
