package com.alsjava.courses.servers.domain.control;

import com.alsjava.courses.servers.domain.AuditDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products")
public class Product extends AuditDomain {

    @NotBlank
    @Column(length = 1000)
    private String name;

    @Column
    private long quantity = 0L;

    @Column
    private double price = 0;

    @Column(columnDefinition = "TEXT")
    private String image; // Imagen en Base64
}
