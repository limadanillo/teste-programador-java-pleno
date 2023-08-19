package com.example.gestao_pedidos.core.useCases;

import com.example.gestao_pedidos.core.dtos.product.ProductSearchRequest;
import com.example.gestao_pedidos.core.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductEntity> findAll(ProductSearchRequest searchRequest);
}
