package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.response.OrderResponse;

public interface OrderService {
    OrderResponse getOrderByOrderId(Long orderId);

    OrderResponse getOrderByUserId(Long userId);
}
