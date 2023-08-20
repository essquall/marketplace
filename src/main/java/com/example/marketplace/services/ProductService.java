package com.example.marketplace.services;


import com.example.marketplace.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getListProducts();

    List<Product> getProductByTitle(String title);

    void saveProduct(Product product, MultipartFile file1, MultipartFile file2);

    void deleteProduct(Long id);

    Product getProductById(Long id);

}
