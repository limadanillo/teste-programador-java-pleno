package com.example.gestao_pedidos.core.useCases.specification;

import com.example.gestao_pedidos.core.dtos.product.ProductSearchRequest;
import com.example.gestao_pedidos.core.entity.ProductEntity;
import com.example.gestao_pedidos.utils.SpecificationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ProductSpecification {
    private static final String PRODUCT_UNIT = "unit";
    private static final String PRODUCT_DESCRIPTION = "description";

    public ProductSpecification() {
    }

    public static Specification<ProductEntity> where(ProductSearchRequest searchRequest) {
        return  Specification.where(Objects.requireNonNull(findByDescription(searchRequest.getDescription())))
                .and(Objects.requireNonNull(findByUnit(searchRequest.getUnit())));
    }

    private static Specification<ProductEntity> findByDescription(String description){
        return StringUtils.isBlank(description) ? getProductEntitySpecification()
                : ((transfer, query, criteriaBuilder) -> criteriaBuilder.like(transfer.get(PRODUCT_DESCRIPTION), SpecificationUtils.like(description)));
    }

    private static Specification<ProductEntity> findByUnit(String unit){
        return StringUtils.isBlank(unit) ? getProductEntitySpecification()
                : ((transfer, query, criteriaBuilder) -> criteriaBuilder.like(transfer.get(PRODUCT_UNIT), SpecificationUtils.like(unit)));
    }

    private static Specification<ProductEntity> getProductEntitySpecification() {
        return (transfer, query, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
