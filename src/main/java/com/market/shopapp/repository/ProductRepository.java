/**
 @author nick.drakopoulos
 */

package com.market.shopapp.repository;

import com.market.shopapp.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
