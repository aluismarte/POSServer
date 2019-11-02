package com.alsjava.courses.servers.repository.control;

import com.alsjava.courses.servers.domain.control.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aluis on 11/2/19.
 */
@Repository
public interface ProductRespository extends JpaRepository<Product, Long> {
}
