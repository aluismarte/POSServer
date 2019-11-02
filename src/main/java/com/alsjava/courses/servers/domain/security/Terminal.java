package com.alsjava.courses.servers.domain.security;

import com.alsjava.courses.servers.domain.AuditDomain;
import com.alsjava.courses.servers.utils.Constants;
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
@Table(name = "terminals")
public class Terminal extends AuditDomain {

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Column(length = Constants.USERNAME_LENGTH, unique = true)
    private String username;

    @NotBlank
    @Column(length = 1000)
    private String password;
}
