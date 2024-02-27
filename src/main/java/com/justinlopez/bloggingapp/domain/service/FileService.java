package com.justinlopez.bloggingapp.domain.service;

import com.justinlopez.bloggingapp.domain.usecase.IFileUseCase;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService implements IFileUseCase {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // Get the file name
        String name = file.getOriginalFilename();

        // Generate a random ID
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        // Full path
        String filePath = path + File.separator + fileName;

        // Create folder if not exists
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        // Copy file to the target location
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;

        return new FileInputStream(fullPath);

    }
}
