package com.foundationProject.BookStore.repository;

import com.foundationProject.BookStore.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o where o.user.userId=:userId")
    Optional<Order> findByUserId(Long userId);

    @Query("from Order o where o.order.status=:status and o.user.userId=:userId")
    Page<Order> findAllByStatusAndUserIdAsPage(boolean status, Long userId);

    @Query("from Order o where o.order.status=:status and o.user.userId=:userId")
    List<Order> findAllByStatusAndUserIdAsList(boolean status, Long userId);
}
