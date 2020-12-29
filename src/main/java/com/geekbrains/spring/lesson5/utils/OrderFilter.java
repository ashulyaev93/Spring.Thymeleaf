package com.geekbrains.spring.lesson5.utils;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.entities.Product;
import com.geekbrains.spring.lesson5.repositories.specifications.OrderSpecifications;
import com.geekbrains.spring.lesson5.repositories.specifications.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class OrderFilter {
    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        //работает!
        String filterCode = params.get("code");
        if (filterCode != null && !filterCode.isBlank()) {
            spec = spec.and(OrderSpecifications.codeLike(filterCode));
            filterDefinitionBuilder.append("&code=").append(filterCode);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isBlank()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(OrderSpecifications.priceOrderGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isBlank()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(OrderSpecifications.priceOrderLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Order> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Order> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
