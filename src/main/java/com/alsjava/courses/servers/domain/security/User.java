package com.alsjava.courses.servers.domain.security;

import com.alsjava.courses.servers.domain.AuditDomain;
import com.alsjava.courses.servers.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends AuditDomain {

    @NotBlank
    @Column(length = Constants.USERNAME_LENGTH, unique = true)
    private String username;

    @NotBlank
    @Column(length = 1000)
    private String password;

    @NotBlank
    @Column(length = 100)
    private String firstNames;

    @NotBlank
    @Column(length = 100)
    private String lastNames;

    @Column
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 30)
    private String phone;
}
