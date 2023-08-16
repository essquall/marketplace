package com.example.marketplace.services;

import com.example.marketplace.model.Picture;
import com.example.marketplace.model.Product;
import com.example.marketplace.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getListProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2) {
        Picture picture1 = parseToPicture(file1);
        Picture picture2 = parseToPicture(file2);
        product.addPicture(picture1);
        product.addPicture(picture2);

        product.setCreationTime(LocalDateTime.now());
        productRepository.save(product);
    }

    private Picture parseToPicture(MultipartFile file) {
        Picture picture = new Picture();
        try {
            picture.setName(file.getName());
            picture.setFileName(file.getOriginalFilename());
            picture.setContentType(file.getContentType());
            picture.setSize(file.getSize());
            picture.setBytes(file.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return picture;
    }
}
