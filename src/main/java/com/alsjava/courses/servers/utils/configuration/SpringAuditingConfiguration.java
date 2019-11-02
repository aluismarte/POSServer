package com.alsjava.courses.servers.utils.configuration;

import com.alsjava.courses.servers.model.LoginSession;
import com.alsjava.courses.servers.utils.Constants;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by aluis on 11/2/19.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class SpringAuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            VaadinSession current = VaadinSession.getCurrent();
            if (current != null) {
                AtomicReference<String> username = new AtomicReference<>(Constants.SYSTEM);
                UI.getCurrent().accessSynchronously((Command) () -> {
                    LoginSession loginSession = current.getAttribute(LoginSession.class);
                    if (loginSession != null) {
                        username.set(loginSession.getUser().getUsername());
                    }
                });
                return Optional.ofNullable(username.get());
            }
            return Optional.of(Constants.SYSTEM);
        };
    }
}
