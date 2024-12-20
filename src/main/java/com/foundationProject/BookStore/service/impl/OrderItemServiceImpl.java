package com.foundationProject.BookStore.service.impl;


import com.foundationProject.BookStore.exception.AppException;
import com.foundationProject.BookStore.exception.ErrorCode;
import com.foundationProject.BookStore.exception.ResourceNotFoundException;
import com.foundationProject.BookStore.model.dto.OrderItemDto;
import com.foundationProject.BookStore.model.entity.Book;
import com.foundationProject.BookStore.model.entity.Order;
import com.foundationProject.BookStore.model.entity.OrderItem;
import com.foundationProject.BookStore.model.entity.User;
import com.foundationProject.BookStore.model.response.OrderItemResponse;
import com.foundationProject.BookStore.model.response.PageCustom;
import com.foundationProject.BookStore.repository.BookRepository;
import com.foundationProject.BookStore.repository.OrderItemRepository;
import com.foundationProject.BookStore.repository.OrderRepository;
import com.foundationProject.BookStore.repository.UserRepository;
import com.foundationProject.BookStore.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Override
    public OrderItemResponse createOrderItem(OrderItemDto orderItemRequest){

        if (orderItemRepository.existsByOrderIdAndStatusAndBookId(orderItemRequest.getBookId(), false, orderItemRequest.getBookId())) {
            OrderItem orderItem = orderItemRepository.findByOrderIdAndStatusAndBookId(orderItemRequest.getBookId(), false, orderItemRequest.getBookId()).get();
            orderItem.setQuantity(orderItem.getQuantity() + orderItemRequest.getQuantity());
            orderItem.setTotalPrice(orderItem.getQuantity() * orderItem.getBook().getUnitPrice());
            return modelMapper.map(orderItemRepository.save(orderItem), OrderItemResponse.class);
        }

        OrderItem orderItem = modelMapper.map(orderItemRequest, OrderItem.class);
        User user =  userRepository.findById(orderItemRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", orderItemRequest.getUserId()));
        orderItem.setUser(user);
        Order order = orderRepository.findByUserId(orderItemRequest.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Order", "userId", orderItemRequest.getUserId()));
        orderItem.setOrder(order);
        Book book = bookRepository.findById(orderItemRequest.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Book", "id", orderItemRequest.getBookId()));
        orderItem.setBook(book);
        orderItem.setTotalPrice(orderItem.getQuantity() * book.getUnitPrice());

        return modelMapper.map(orderItemRepository.save(orderItem), OrderItemResponse.class);
    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemDto orderItemRequest){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));
        orderItem.setQuantity(orderItemRequest.getQuantity());
        orderItem.setTotalPrice(orderItem.getQuantity() * orderItem.getBook().getUnitPrice());
        return modelMapper.map(orderItemRepository.save(orderItem), OrderItemResponse.class);
    }

    @Override
    public String updateOrderItemStatus(Long id){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));
        orderItem.setStatus(true);
        orderItemRepository.save(orderItem);
        return "OrderItem with id: " +id+ " was updated successfully";
    }

    @Override
    public String deleteOrderItem(Long id){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));
        orderItemRepository.delete(orderItem);
        return "OrderItem with id: " +id+ " was deleted successfully";
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id){
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "id", id));
        return modelMapper.map(orderItem, OrderItemResponse.class);
    }

    @Override
    public PageCustom<OrderItemResponse> getOrderItemByOrderId(Long orderId, Pageable pageable){
        Page<OrderItem> orderItem = orderItemRepository.findAllByOrderIdAndStatus(orderId,false,pageable);
         PageCustom<OrderItemResponse> pageCustom = PageCustom.<OrderItemResponse>builder()
                .pageNo(orderItem.getNumber() + 1)
                .pageSize(orderItem.getSize())
                .totalPages(orderItem.getTotalPages())
                .pageContent(orderItem.getContent().stream().map(orderItem1 -> modelMapper.map(orderItem1, OrderItemResponse.class)).toList())
                .build();
         return pageCustom;
    }

    @Override
    public PageCustom<OrderItemResponse> getAllOrderItem(Pageable pageable){
        Page<OrderItem> orderItem = orderItemRepository.findAll(pageable);
         PageCustom<OrderItemResponse> pageCustom = PageCustom.<OrderItemResponse>builder()
                .pageNo(orderItem.getNumber() + 1)
                .pageSize(orderItem.getSize())
                .totalPages(orderItem.getTotalPages())
                .pageContent(orderItem.getContent().stream().map(orderItem1 -> modelMapper.map(orderItem1, OrderItemResponse.class)).toList())
                .build();
         return pageCustom;
    }

    @Override
    public PageCustom<OrderItemResponse> getAllOrderedHistory(Long orderId, Pageable pageable){
        Page<OrderItem> orderItem = orderItemRepository.findAllByOrderIdAndStatus(orderId,true,pageable);
         PageCustom<OrderItemResponse> pageCustom = PageCustom.<OrderItemResponse>builder()
                .pageNo(orderItem.getNumber() + 1)
                .pageSize(orderItem.getSize())
                .totalPages(orderItem.getTotalPages())
                .pageContent(orderItem.getContent().stream().map(orderItem1 -> modelMapper.map(orderItem1, OrderItemResponse.class)).toList())
                .build();
         return pageCustom;
    }

    @Override
    public PageCustom<OrderItemResponse> getAllOrderedHistoryByUserId(Long userId,Pageable pageable){
        Page<OrderItem> orderItem = orderItemRepository.findAllByUserIdAndStatus(userId,true,pageable);
         PageCustom<OrderItemResponse> pageCustom = PageCustom.<OrderItemResponse>builder()
                .pageNo(orderItem.getNumber() + 1)
                .pageSize(orderItem.getSize())
                .totalPages(orderItem.getTotalPages())
                .pageContent(orderItem.getContent().stream().map(orderItem1 -> modelMapper.map(orderItem1, OrderItemResponse.class)).toList())
                .build();
         return pageCustom;
    }



    @Override
    public PageCustom<OrderItemResponse> getAllCartItemsByUserId(Long userId, Pageable pageable) {
        List<Order> orders = orderRepository.findAllByStatusAndUserIdAsList(false, userId);
        if (orders.size() != 1) {
            throw new AppException(ErrorCode.CART_NOT_UNIQUE);
        }
        Page<OrderItem> orderItems = orderItemRepository.findAllByUserIdAndStatus(userId,false,pageable);
        PageCustom<OrderItemResponse> pageCustom = PageCustom.<OrderItemResponse>builder()
                .pageNo(orderItems.getNumber() + 1)
                .pageSize(orderItems.getSize())
                .totalPages(orderItems.getTotalPages())
                .pageContent(orderItems.getContent().stream().map(orderItem -> modelMapper.map(orderItem, OrderItemResponse.class)).toList())
                .build();
        return pageCustom;
    }


}
