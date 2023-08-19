package com.example.gestao_pedidos.core.useCases;

import com.example.gestao_pedidos.core.dtos.product.ProductSearchRequest;
import com.example.gestao_pedidos.core.entity.ProductEntity;
import com.example.gestao_pedidos.core.useCases.specification.ProductSpecification;
import com.example.gestao_pedidos.dataprovider.database.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

//TODO Migrar todos os metódos do controller para o service
// Implementar testes unitários
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductEntity> findAll(ProductSearchRequest searchRequest) {
        return productRepository.findAll(ProductSpecification.where(searchRequest), PageRequest.of(searchRequest.getPage(), searchRequest.getSize()));
    }
}
