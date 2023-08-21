package com.example.marketplace.services;


import com.example.marketplace.model.Product;
import com.example.marketplace.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ProductService {

    List<Product> getListProducts();

    List<Product> getProductByTitle(String title);

    void saveProduct(Product product, MultipartFile file1, MultipartFile file2, Principal principal);

    User getUserByPrincipal(Principal principal);

    void deleteProduct(Long id);

    Product getProductById(Long id);

}
