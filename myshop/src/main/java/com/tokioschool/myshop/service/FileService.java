package com.tokioschool.myshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service para gestión de ficheros
 */
public interface FileService {
    String uploadFile(MultipartFile file, String user);
    void createDirectory(String film);
}
