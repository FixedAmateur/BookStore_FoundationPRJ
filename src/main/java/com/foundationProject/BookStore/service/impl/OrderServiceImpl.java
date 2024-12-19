package com.foundationProject.BookStore.service.impl;


import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.entity.Order;
import com.foundationProject.BookStore.model.response.OrderResponse;
import com.foundationProject.BookStore.repository.OrderRepository;
import com.foundationProject.BookStore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponse getOrderByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;
    }

    @Override
    public OrderResponse getOrderByUserId(Long userId) {
        Order order = orderRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Order", "userId", userId));
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;
    }

}
