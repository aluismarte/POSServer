package com.alsjava.courses.servers.domain.control;

import com.alsjava.courses.servers.domain.AuditDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "invoices")
public class Invoice extends AuditDomain {
}
