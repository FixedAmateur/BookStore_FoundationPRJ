package com.foundationProject.BookStore.service;

import com.foundationProject.BookStore.model.dto.OrderItemDto;
import com.foundationProject.BookStore.model.response.OrderItemResponse;
import com.foundationProject.BookStore.model.response.PageCustom;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {

    OrderItemResponse createOrderItem(OrderItemDto orderItemRequest);

    OrderItemResponse updateOrderItem(Long id, OrderItemDto orderItemRequest);

    String updateOrderItemStatus(Long id);

    String deleteOrderItem(Long id);

    OrderItemResponse getOrderItemById(Long id);

    PageCustom<OrderItemResponse> getOrderItemByOrderId(Long orderId, Pageable pageable);

    PageCustom<OrderItemResponse> getAllOrderItem(Pageable pageable);

    PageCustom<OrderItemResponse> getAllOrderedHistory(Long orderId, Pageable pageable);

    PageCustom<OrderItemResponse> getAllOrderedHistoryByUserId(Long userId, Pageable pageable);

    PageCustom<OrderItemResponse> getAllOrderItemInCart(Long userId, Pageable pageable);
}
