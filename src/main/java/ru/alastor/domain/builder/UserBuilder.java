package ru.alastor.domain.builder;

import ru.alastor.domain.RoleUser;
import ru.alastor.domain.User;

/**
 * Created on 02.10.17.
 *
 * @author Maxim Goncharov
 */
public class UserBuilder {

    private static UserBuilder ourInstance = new UserBuilder();

    public static UserBuilder getInstance() {
        return ourInstance;
    }

    private static User user;

    private UserBuilder() {
    }

    public UserBuilder createUser() {
        user = new User();
        return ourInstance;
    }

    public UserBuilder setLogin(String login) {
        user.setLogin(login);
        return ourInstance;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return ourInstance;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return ourInstance;
    }

    public UserBuilder setRole(RoleUser role) {
        user.setRole(role);
        return ourInstance;
    }

    public UserBuilder setAboutUser(String aboutUser) {
        user.setAboutUser(aboutUser);
        return ourInstance;
    }

    public UserBuilder setName(String name) {
        user.setName(name);
        return ourInstance;
    }

    public UserBuilder setSurname(String name) {
        user.setSurname(name);
        return ourInstance;
    }

    public User getUser() {
        return user;
    }
}
