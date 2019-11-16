package com.alsjava.courses.servers.repository.security;

import com.alsjava.courses.servers.domain.security.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aluis on 11/2/19.
 */
@Repository
public interface TerminalRespository extends JpaRepository<Terminal, Long> {

    Terminal findByUsernameAndPasswordAndEnabledIsTrue(String username, String password);

    Terminal findByIdAndEnabledIsTrue(Long id);
}
