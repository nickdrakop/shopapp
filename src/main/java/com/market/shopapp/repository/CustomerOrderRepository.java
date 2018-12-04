/**
 @author nick.drakopoulos
 */

package com.market.shopapp.repository;

import com.market.shopapp.domain.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {

    @Query(value = "SELECT * FROM customer_order WHERE created_at >= :startDate and created_at < :endDate",
        nativeQuery = true)
    List<CustomerOrderEntity> findAll(Instant startDate, Instant endDate);
}
