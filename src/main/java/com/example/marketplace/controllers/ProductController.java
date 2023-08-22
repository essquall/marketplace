package com.example.marketplace.controllers;

import com.example.marketplace.model.Product;
import com.example.marketplace.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String getProducts(@RequestParam(name = "title", required = false) String title, Model model, Principal principal) {
        model.addAttribute("products", productService.getListProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProductInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product)
                .addAttribute("pictures", product.getPictures());
        return "product-info";
    }

    @PostMapping("/products/")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,Product product, Principal principal) {
        productService.saveProduct(product, file1, file2, principal);
        return "redirect:/";
    }

    @PostMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
