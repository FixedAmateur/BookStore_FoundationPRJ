package com.foundationProject.BookStore.controller;


import com.foundationProject.BookStore.model.dto.OrderItemDto;
import com.foundationProject.BookStore.model.response.ApiResponse;
import com.foundationProject.BookStore.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
@Tag(name = "Order Item Controller", description = "Manage Order Items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "Create Order Item", description = "Create Order Item API")
    @PostMapping()
    public ResponseEntity<ApiResponse> createOrderItem(@RequestBody @Valid OrderItemDto orderItemRequest) {
        ApiResponse apiResponse = ApiResponse.success(orderItemService.createOrderItem(orderItemRequest));
        return  new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Order Item", description = "Update Order Item API")
    @PutMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse> updateOrderItem(@PathVariable("orderItemId") Long orderItemId, @RequestBody @Valid OrderItemDto orderItemRequest) {
        ApiResponse apiResponse = ApiResponse.success(orderItemService.updateOrderItem(orderItemId, orderItemRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete Order Item", description = "Delete Order Item API")
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse> deleteOrderItem(@PathVariable("orderItemId") Long orderItemId) {
        ApiResponse apiResponse = ApiResponse.success(orderItemService.deleteOrderItem(orderItemId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Order Item By Id", description = "Get Order Item By Id API")
    @GetMapping("/{orderItemId}")
    public ResponseEntity<ApiResponse> getOrderItemById(@PathVariable("orderItemId") Long orderItemId) {
        ApiResponse apiResponse = ApiResponse.success(orderItemService.getOrderItemById(orderItemId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Order Item Status", description = "Update Order Item Status API")
    @PutMapping("/update-status/{orderItemId}")
    public ResponseEntity<ApiResponse> updateOrderItemStatus(@PathVariable("orderItemId") Long orderItemId) {
        System.out.println("hello");
        ApiResponse apiResponse = ApiResponse.success(orderItemService.updateOrderItemStatus(orderItemId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get All Order Items", description = "Get All Order Items API")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllOrderItems(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(orderItemService.getAllOrderItem(pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Order Item By Order Id", description = "Get Order Item By Order Id API")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderItemByOrderId(@PathVariable("orderId") Long orderId,
    @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
       @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(orderItemService.getOrderItemByOrderId(orderId,pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Order Item History By Order Id", description = "Get Order Item History By Order Id API")
    @GetMapping("/history/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderHistoryByOrderId(
            @PathVariable("orderId") Long orderId,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(orderItemService.getAllOrderedHistory(orderId,pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Order Item History By User Id", description = "Get Order Item History By User Id API")
    @GetMapping("/history/user/{userId}")
    public ResponseEntity<ApiResponse> getOrderHistoryByUserId(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(orderItemService.getAllOrderedHistoryByUserId(userId,pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
