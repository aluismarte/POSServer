package com.alsjava.courses.servers.controller;

import com.alsjava.courses.servers.domain.security.Terminal;
import com.alsjava.courses.servers.model.CommunicationConstants;
import com.alsjava.courses.servers.model.communication.CommunicationCodes;
import com.alsjava.courses.servers.model.communication.request.LoginRequest;
import com.alsjava.courses.servers.model.communication.response.LoginResponse;
import com.alsjava.courses.servers.repository.security.TerminalRespository;
import com.alsjava.courses.servers.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aluis on 11/2/19.
 */
@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private TerminalRespository terminalRespository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestParam(value = CommunicationConstants.SESSION_FORM_RESOURCE) String session, @RequestParam(value = CommunicationConstants.DATA_FORM_RESOURCE) String request) {
        LoginRequest loginRequest = Constants.get().convert(request, LoginRequest.class);
        if (loginRequest == null) {
            return ResponseEntity.ok(new LoginResponse(CommunicationCodes.SYNTAX_ERROR));
        }
        Terminal terminal = terminalRespository.findByUsernameAndPasswordAndEnabledIsTrue(loginRequest.getUsername(), loginRequest.getPassword());
        if (terminal == null) {
            return ResponseEntity.ok(new LoginResponse(CommunicationCodes.TERMINAL_NO_FOUND));
        }
        String token = Constants.LOGIN_MANAGER.login(terminal.getId());
        LoginResponse loginResponse = new LoginResponse(CommunicationCodes.OK);
        loginResponse.setSession(token);
        loginResponse.setTerminal(terminal.getId());
        return ResponseEntity.ok(new LoginResponse(token, terminal.getId(), terminal.getName()));
    }

    // Necesito servicio para solicitar las facturas.
    // Necesito servicio para solicitar los productos..
    // Necesito servicio para solicitar el reporte...
}
