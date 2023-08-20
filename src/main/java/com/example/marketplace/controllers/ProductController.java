package com.example.marketplace.controllers;

import com.example.marketplace.model.Product;
import com.example.marketplace.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getListProducts());
        return "products";
    }

    @GetMapping("/search/")
    public String searchProducts(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.getProductByTitle(title));
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
                                @RequestParam("file2") MultipartFile file2, Product product) {
        productService.saveProduct(product, file1, file2);
        return "redirect:/";
    }

    @PostMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

}
