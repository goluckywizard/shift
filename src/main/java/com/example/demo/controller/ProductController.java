package com.example.demo.controller;

import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/get/type")
    public ResponseEntity<?> getProductsByType(@RequestBody String type) {
        try {
            List<ProductModel> modelList = productService.getProductsByName(type);
            return ResponseEntity.ok(modelList);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductModel model) {
        try {
            productService.addProduct(model);
            return ResponseEntity.ok("Product added");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            ProductModel model = productService.getProductById(id);
            return ResponseEntity.ok(model);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/change/{id}")
    public ResponseEntity<?> changeProduct(@PathVariable Long id, @RequestBody ProductModel model) {
        try {
            ProductModel productModel = productService.changeProduct(model, id);
            return ResponseEntity.ok(model);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
