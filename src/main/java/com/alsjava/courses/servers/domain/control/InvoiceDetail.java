package com.alsjava.courses.servers.domain.control;

import com.alsjava.courses.servers.domain.AuditDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "invoice_details")
public class InvoiceDetail extends AuditDomain {

    @OneToOne
    private Product product;

    @Column
    private long quantity = 0L;

    @Column
    private double price = 0;
}
