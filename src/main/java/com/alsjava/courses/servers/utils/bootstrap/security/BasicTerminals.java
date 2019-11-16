package com.alsjava.courses.servers.utils.bootstrap.security;

import com.alsjava.courses.servers.domain.security.Terminal;
import com.alsjava.courses.servers.model.security.HashTools;
import com.alsjava.courses.servers.repository.security.TerminalRespository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aluis on 11/2/19.
 */
public class BasicTerminals implements BootStrapInsert {

    @Autowired
    private TerminalRespository terminalRespository;

    public BasicTerminals() {
        Constants.get().autoWiredClass(this);
    }

    @Override
    public void insert() {
        create("TM1", "demo", "demo");
        create("TM2", "terminal", "terminal");
        create("Terminal Jeimy", "jeimy", "123456");
        create("Terminal maria", "maria", "123456");
        create("Terminal Rebeca", "rebeca", "123456");
    }

    private Terminal create(String name, String username, String password) {
        password = HashTools.encodeSHA512(password);
        Terminal terminal = terminalRespository.findByUsernameAndPasswordAndEnabledIsTrue(username, password);
        if (terminal == null) {
            terminal = new Terminal();
            terminal.setName(name);
            terminal.setUsername(username);
            terminal.setPassword(password);
            return terminalRespository.saveAndFlush(terminal);
        }
        return terminal;
    }
}
