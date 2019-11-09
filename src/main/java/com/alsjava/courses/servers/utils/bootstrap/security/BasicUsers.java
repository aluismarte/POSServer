package com.alsjava.courses.servers.utils.bootstrap.security;

import com.alsjava.courses.servers.domain.security.User;
import com.alsjava.courses.servers.model.security.HashTools;
import com.alsjava.courses.servers.repository.security.UserRepository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * Created by aluis on 11/2/19.
 */
public class BasicUsers implements BootStrapInsert {

    @Autowired
    private UserRepository userRepository;

    public BasicUsers() {
        Constants.get().autoWiredClass(this);
    }

    @Override
    public void insert() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 8);
        create("root", "Root", "Root", "System", calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "root@alsjava.com");
        create("demo", "demo", "demo", "user", calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "demo@alsjava.com");
    }

    private User create(String username, String password, String firstNames, String lastNames, LocalDate birthday, String email) {
        password = HashTools.encodeSHA512(password);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstNames(firstNames);
            user.setLastNames(lastNames);
            user.setBirthday(birthday);
            user.setEmail(email);
            userRepository.saveAndFlush(user);
        }
        return user;
    }
}
