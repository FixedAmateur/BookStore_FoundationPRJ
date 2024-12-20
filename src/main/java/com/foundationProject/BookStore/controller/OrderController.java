package com.foundationProject.BookStore.controller;


import com.foundationProject.BookStore.model.response.ApiResponse;
import com.foundationProject.BookStore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order API")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get Order By Order Id", description = "Get Order By Order Id API")
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse> getOrderByOrderId(@PathVariable("orderId") Long orderId) {
        ApiResponse apiResponse = ApiResponse.success(orderService.getOrderByOrderId(orderId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get Order By User Id", description = "Get Order By User Id API")
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getOrderByUserId(@PathVariable("userId") Long userId) {
        ApiResponse apiResponse = ApiResponse.success(orderService.getOrderByUserId(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



    @Operation(summary = "Get All Order in History from User Id", description = "Get order with true status by User Id API")
    @GetMapping("user/{userId}/history")
    public ResponseEntity<ApiResponse> getOrderHistoryByUserId (@PathVariable("userId") Long userId,
                                                                @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                                                                @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy).ascending());
        ApiResponse apiResponse = ApiResponse.success(orderService.getOrderHistoryByUserId(userId,pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
