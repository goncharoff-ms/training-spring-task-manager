package ru.alastor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.User;

/**
 * Created on 18.09.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User getByLogin(final String login);
}
