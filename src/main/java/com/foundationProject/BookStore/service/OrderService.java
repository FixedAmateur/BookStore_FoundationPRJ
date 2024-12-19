package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.response.OrderItemResponse;
import com.foundationProject.BookStore.model.response.OrderResponse;
import com.foundationProject.BookStore.model.response.PageCustom;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderResponse getOrderByOrderId(Long orderId);

    OrderResponse getOrderByUserId(Long userId);

    //find order with status false and user id userId
    OrderResponse getCart(Long userId);

    PageCustom<OrderResponse> getOrderHistoryByUserId(Long userId, Pageable pageable);
}
