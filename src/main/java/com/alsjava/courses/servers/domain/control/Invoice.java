package com.alsjava.courses.servers.domain.control;

import com.alsjava.courses.servers.domain.AuditDomain;
import com.alsjava.courses.servers.domain.security.Terminal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "invoices")
public class Invoice extends AuditDomain {

    @Column
    private Terminal terminal;

    @Column
    private String serial;

    @Column
    private LocalDateTime saleDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @CollectionTable(name = "invoices_invoiceDetails")
    private List<InvoiceDetail> invoiceDetails;
}
