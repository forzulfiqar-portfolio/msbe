package com.example.productservice.controller;

import com.example.common_lib.SubmitResult;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Create product (raw Product response)
    @PostMapping("/addproduct")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    // Create product and return SubmitResult wrapper
    @PostMapping("/addproductsubmit")
    public SubmitResult<Product> createProductSubmit(@RequestBody Product product) {
        Product saved = service.createProduct(product);
        return SubmitResult.ok(saved);
    }

    // Raw list of products
    @GetMapping("/listproducts")
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    // SubmitResult-wrapped list of products
    @GetMapping("/listproductssubmit")
    public SubmitResult getProductsSubmitResult() {
    	SubmitResult result = new SubmitResult();
    	try {
    		List<Product> products = service.getAllProducts();
    		result = SubmitResult.ok(products);
    	} catch(Exception e) {
    		result = SubmitResult.fail(e.getMessage());
    	}
        return result;
    }
}
