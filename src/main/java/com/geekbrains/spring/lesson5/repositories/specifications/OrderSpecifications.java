package com.geekbrains.spring.lesson5.repositories.specifications;

import com.geekbrains.spring.lesson5.entities.Customer;
import com.geekbrains.spring.lesson5.entities.Order;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {
    public static Specification<Order> priceOrderGreaterOrEqualsThan(int minPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("currentPrice"), minPrice);  // where o.currentPrice >= minPrice
    }

    public static Specification<Order> priceOrderLesserOrEqualsThan(int maxPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("currentPrice"), maxPrice); // where o.currentPrice <= maxPrice
    }

    public static Specification<Order> codeLike(String codePart) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("code"), String.format("%%%s%%", codePart)); // where o.code like %codePart%
    }

//    *********************************//Никак не получается обратиться к name в customer!!! Остальные задания работают.
//    public static Specification<Order> nameCustomerLike(String namePart) {
//        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("customer"), Customer.name("%%%s%%", namePart)); // where c.customer like %nameCustomerLike%
//    }

}
