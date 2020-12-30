package com.geekbrains.spring.lesson5.utils;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.repositories.specifications.OrderSpecifications;
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

        if (params.containsKey("min_currentPrice") && !params.get("min_currentPrice").isBlank()) {
            Integer minCurrentPrice = Integer.parseInt(params.get("min_currentPrice"));
            spec = spec.and(OrderSpecifications.priceOrderGreaterOrEqualsThan(minCurrentPrice));
            filterDefinitionBuilder.append("&min_currentPrice=").append(minCurrentPrice);
        }

        if (params.containsKey("max_currentPrice") && !params.get("max_currentPrice").isBlank()) {
            Integer maxCurrentPrice = Integer.parseInt(params.get("max_currentPrice"));
            spec = spec.and(OrderSpecifications.priceOrderLesserOrEqualsThan(maxCurrentPrice));
            filterDefinitionBuilder.append("&max_currentPrice=").append(maxCurrentPrice);
        }

//        ********************
//        String filterName = params.get("nameCustomer");
//        if (filterName != null && !filterName.isBlank()) {
//            spec = spec.and(OrderSpecifications.nameCustomerLike(filterName));
//            filterDefinitionBuilder.append("&nameCustomer=").append(filterName);
//        }

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
