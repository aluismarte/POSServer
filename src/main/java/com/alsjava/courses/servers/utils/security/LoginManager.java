package com.alsjava.courses.servers.utils.security;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by aluis on 8/21/19.
 */
public class LoginManager {

    private final List<OnlineTerminal> onlineTerminals = Collections.synchronizedList(new ArrayList<>());

    private final Comparator<OnlineTerminal> onlineTerminalComparator = Comparator.comparing(OnlineTerminal::getSession);

    /**
     * Time limit default (15 minutes)
     */
    private long timeExpire = 15 * 60;

    public void setTimeExpire(long timeExpire) {
        this.timeExpire = timeExpire;
    }

    public Long getTerminal(String session) {
        OnlineTerminal onlineTerminal = find(session);
        if (onlineTerminal != null) {
            if (checkExpirationTime(onlineTerminal.getLastActivity())) {
                return null;
            }
            return onlineTerminal.getTerminal();
        }
        return null;
    }

    public String relogin(String session) {
        OnlineTerminal onlineTerminal = find(session);
        if (onlineTerminal != null) {
            onlineTerminal.setSession(IDGenerator.generateUUID());
            onlineTerminal.setLastActivity(LocalDateTime.now());
            return onlineTerminal.getSession();
        }
        return null;
    }

    public String login(Long terminal) {
        if (isLoginByID(terminal)) {
            OnlineTerminal onlineTerminal = revalidate(terminal);
            onlineTerminals.sort(onlineTerminalComparator);
            return onlineTerminal.getSession();
        }
        OnlineTerminal onlineTerminal = new OnlineTerminal(terminal);
        onlineTerminals.add(onlineTerminal);
        onlineTerminals.sort(onlineTerminalComparator);
        return onlineTerminal.getSession();
    }

    public boolean logout(String session) {
        return onlineTerminals.remove(find(session));
    }

    public boolean isLogin(String session) {
        OnlineTerminal onlineTerminal = find(session);
        if (onlineTerminal != null) {
            onlineTerminal.setLastActivity(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public Long expireTerminalID(String session) {
        OnlineTerminal onlineTerminal = find(session);
        if (onlineTerminal != null) {
            return onlineTerminal.getTerminal();
        }
        return null;
    }

    public boolean isOnMemory(String session) {
        return find(session) != null;
    }

    private boolean isLoginByID(Long terminal) {
        OnlineTerminal onlineTerminal = getOnlineTerminal(terminal);
        if (onlineTerminal != null) {
            if (checkExpirationTime(onlineTerminal.getLastActivity())) {
                return false;
            }
            onlineTerminal.setLastActivity(LocalDateTime.now());
            return true;
        }
        return false;
    }

    private OnlineTerminal find(String session) {
        int pos = Collections.binarySearch(onlineTerminals, new OnlineTerminal(session), onlineTerminalComparator);
        if (pos < 0) {
            return null;
        }
        return onlineTerminals.get(pos);
    }

    private OnlineTerminal getOnlineTerminal(Long terminal) {
        return onlineTerminals.stream().filter(onlineTerminal -> onlineTerminal.getTerminal().equals(terminal)).findFirst().orElse(null);
    }

    private boolean checkExpirationTime(LocalDateTime lastActivity) {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), lastActivity) > timeExpire;
    }

    /**
     * Revalida la session
     */
    private OnlineTerminal revalidate(long terminal) {
        OnlineTerminal onlineTerminal = getOnlineTerminal(terminal);
        assert onlineTerminal != null;
        if (!checkExpirationTime(onlineTerminal.getLastActivity())) {
            return onlineTerminal;
        }
        return new OnlineTerminal(terminal);
    }

    @Data
    private static class OnlineTerminal {

        private String session; // Session
        private Long terminal;
        private LocalDateTime lastActivity;

        OnlineTerminal(Long terminal) {
            this.session = IDGenerator.generateUUID();
            this.terminal = terminal;
            this.lastActivity = LocalDateTime.now();
        }

        OnlineTerminal(String session) {
            this.session = session;
        }
    }
}
