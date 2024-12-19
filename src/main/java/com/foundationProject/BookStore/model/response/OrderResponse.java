package com.foundationProject.BookStore.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private int quantity;
    private Long totalPrice;
    private boolean status;
    private Set<OrderItemResponse> orderItems;

}