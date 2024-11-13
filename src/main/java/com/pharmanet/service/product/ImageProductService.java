package com.pharmanet.service.product;

import com.pharmanet.presentation.dto.ImageProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageProductService {
    ImageProductDto update(Long id, MultipartFile file) throws IOException;
}
