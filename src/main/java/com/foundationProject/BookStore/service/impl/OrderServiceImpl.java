package com.foundationProject.BookStore.service.impl;


import com.foundationProject.BookStore.exception.AppException;
import com.foundationProject.BookStore.exception.ErrorCode;
import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.entity.Order;
import com.foundationProject.BookStore.model.response.OrderItemResponse;
import com.foundationProject.BookStore.model.response.OrderResponse;
import com.foundationProject.BookStore.model.response.PageCustom;
import com.foundationProject.BookStore.repository.OrderRepository;
import com.foundationProject.BookStore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public OrderResponse getCart(Long userId) {
        List<Order> cartLst = orderRepository.findAllByStatusAndUserIdAsList(false, userId);
        if (cartLst.size() != 1) {
            throw new AppException(ErrorCode.CART_NOT_UNIQUE);
        }
        Order order = cartLst.get(0);
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);
        return orderResponse;

    }

    @Override
    public PageCustom<OrderResponse> getOrderHistoryByUserId(Long userId, Pageable pageable) {
        Page<Order> orders = orderRepository.findAllByStatusAndUserIdAsPage(true, userId);
        PageCustom<OrderResponse> pageCustom = PageCustom.<OrderResponse>builder()
                .pageNo(orders.getNumber() + 1)
                .pageSize(orders.getSize())
                .totalPages(orders.getTotalPages())
                .pageContent(orders.getContent().stream().map(order -> modelMapper.map(order, OrderResponse.class)).toList())
                .build();
        return pageCustom;
    }

}
