package com.alsjava.courses.servers.repository.security;

import com.alsjava.courses.servers.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by aluis on 11/2/19.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByUsernameAndPasswordAndEnabled(String username, String password, boolean enabled);

}
