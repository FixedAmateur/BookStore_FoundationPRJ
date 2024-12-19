package com.foundationProject.BookStore.repository;

import com.foundationProject.BookStore.model.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    @Query("from OrderItem o where o.order.orderId=:orderId and o.status=:b")
    Page<OrderItem> findAllByOrderIdAndStatus(Long orderId, boolean b, Pageable pageable);

    @Query("from OrderItem o where o.order.user.userId=:userId and o.status=:status")
    Page<OrderItem> findAllByUserIdAndStatus(Long userId, boolean status, Pageable pageable);

    @Query("select case when count(o) > 0 then true else false end from OrderItem o where o.order.orderId=:orderId and o.status=:b and o.book.bookId=:bookId")
    boolean existsByOrderIdAndStatusAndBookId(Long orderId, boolean b, Long bookId);

    @Query("select case when count(o) > 0 then true else false end from OrderItem o where o.user.userId:=userId and o.status=status")
    boolean existsByUserIdAndStatus(Long userId, boolean status);

    @Query("from OrderItem o where o.book.bookId=:bookId and o.status=:b and o.book.bookId=:bookId1")
    Optional<OrderItem> findByOrderIdAndStatusAndBookId(Long bookId, boolean b, Long bookId1);
}
