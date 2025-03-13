
package com.ShoeVibes.repository;

import com.ShoeVibes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
