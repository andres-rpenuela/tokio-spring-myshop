package com.tokioschool.myshop.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tokioschool.myshop.service.FileService;



@Service
public class FileServiceImpl implements FileService {
	
	private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${images.upload.dir}")
    private String uploadDir;
    
    @Value("${images.server.dir}")
    private String severDir;    

    @Override
    public String uploadFile(MultipartFile file, String user) {
    	logger.info("Subo el fichero");
    	
    	Path copyLocation=null;
    	String extension="";
    	
        try {       	        	
        	String workingDir = System.getProperty("user.dir");
            
            int index = file.getOriginalFilename().lastIndexOf('.');
            if(index > 0) {
              extension = file.getOriginalFilename().substring(index + 1);
            }
        	
        	copyLocation = Paths
        			.get(workingDir+uploadDir + File.separator + user+ File.separator+user+"."+extension);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return severDir + File.separator + user+ File.separator+user+"."+extension;
    }
    
    
    public void createDirectory(String film) {
    	
    	logger.info("Creo el directorio donde se guardan las imagenes");
    	String workingDir = System.getProperty("user.dir");
    	String dir=workingDir+uploadDir + File.separator + film;
    	
        File directorio = new File(dir);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                logger.info("Directorio creado");
            } else {
            	logger.info("Error al crear el directorio");
            }
        }
    }
}
