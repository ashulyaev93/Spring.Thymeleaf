package com.geekbrains.spring.lesson5.services;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.entities.Product;
import com.geekbrains.spring.lesson5.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public Optional<Product> findById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    public void deleteById(Long id) {
//        productRepository.deleteById(id);
//    }
//
//    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
//        return productRepository.findAll(spec, PageRequest.of(page, size));
//    }


    public List<Order> findByCode(String code) {
        return orderRepository.findByCode(code);
    }

    public Page<Order> findAll(Specification<Order> spec, int page, int size) {
        return orderRepository.findAll(spec, PageRequest.of(page, size));
    }

}
