package com.pharmanet.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadService {
    private final String url = "upload/";
    private final String defaultImage = "default.jpg";

    public String saveUpload(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            byte[] bytes = file.getBytes();
            // Codificar el nombre del archivo
            String encodedFileName = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
            Path path = Paths.get(url + encodedFileName);
            Files.write(path, bytes);
            return encodedFileName;
        }
        // Retornar la imagen predeterminada si file es null o está vacío
        return defaultImage;
    }

    public void deleteUpload(String nombre) {
        File file = new File(url + nombre);
        file.delete();
    }
}
