package com.example.productservice.endpoint;

import com.example.productservice.dto.ProductRequestDto;
import com.example.productservice.dto.ProductResponseDto;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductEndpoint {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDto productRequestDto  ) {
        productService.createProduct(productRequestDto);
        log.info("create product worked");

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDto> getAllProducts( ) {
       return productService.getAllProducts();
    }
}
