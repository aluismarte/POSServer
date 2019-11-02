package com.alsjava.courses.servers.model.communication.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by aluis on 11/2/19.
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse extends Response {

    private String session;
    private Long terminal;

    public LoginResponse(int code) {
        super(code);
    }
}
