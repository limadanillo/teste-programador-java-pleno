package com.example.gestao_pedidos.entrypoint.facada.rest;

import com.example.gestao_pedidos.core.constrain.NumberConstraint;
import com.example.gestao_pedidos.core.dtos.product.ProductRequest;
import com.example.gestao_pedidos.core.dtos.product.ProductSearchRequest;
import com.example.gestao_pedidos.core.entity.ProductEntity;
import com.example.gestao_pedidos.core.useCases.ProductService;
import com.example.gestao_pedidos.dataprovider.database.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductRepository productRepository;

    private final ProductService productService;
//TODO Converter retorno para padrão
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductEntity> findAll(ProductSearchRequest searchRequest) {
        log.info("findAll init");
        return productService.findAll(searchRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductEntity> findById(@PathVariable String id) {
        log.info("findById init id={}", id);
        return productRepository.findById(Long.valueOf(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity create(@RequestBody ProductRequest productRequest) {
        var product = ProductEntity.builder()
                .unit(productRequest.unit())
                .price(productRequest.price())
                .description(productRequest.description())
                .build();
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = false) @NumberConstraint(placeholder = "Código produto", min = 1)
                           String id) {
        productRepository.deleteById(Long.valueOf(id));
    }
}
