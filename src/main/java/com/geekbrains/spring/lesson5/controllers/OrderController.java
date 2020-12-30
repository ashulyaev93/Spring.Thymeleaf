package com.geekbrains.spring.lesson5.controllers;

import com.geekbrains.spring.lesson5.entities.Order;
import com.geekbrains.spring.lesson5.services.OrderService;
import com.geekbrains.spring.lesson5.utils.OrderFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String firstRequest(Model model,
                               @RequestParam(defaultValue = "1", name = "p") Integer page,
                               @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        OrderFilter orderFilter = new OrderFilter(params);
        Page<Order> orders = orderService.findAll(orderFilter.getSpec(), page - 1, 5);
        model.addAttribute("orders", orders);
        model.addAttribute("filterDefinition", orderFilter.getFilterDefinition());
        return "orders";
    }

    @GetMapping("/{code}")
    @ResponseBody
    public List<Order> getOneOrderByCode(@PathVariable String code) {
        return orderService.findByCode(code);
    }

//    @GetMapping("/delete/{code}")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public String deleteOneOrderByCode(@PathVariable String code) {
//        orderService.deleteByCode(code);
//        return "ok";
//    }
}
