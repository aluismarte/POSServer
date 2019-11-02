package com.alsjava.courses.servers.model;

import com.alsjava.courses.servers.domain.security.User;
import lombok.Data;

/**
 * Created by aluis on 11/2/19.
 */
@Data
public class LoginSession {

    private User user;
}
