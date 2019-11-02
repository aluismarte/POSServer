package com.alsjava.courses.servers.utils.bootstrap.security;

import com.alsjava.courses.servers.repository.security.TerminalRespository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    }
}
