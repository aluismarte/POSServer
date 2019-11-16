package com.alsjava.courses.servers.repository.control;

import com.alsjava.courses.servers.domain.control.Invoice;
import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by aluis on 11/2/19.
 */
@Repository
public interface InvoiceRespository extends JpaRepository<Invoice, Long> {

    Invoice findBySerial(String serial);

    List<Invoice> findAllByEnabledIsTrueAndSaleDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
