package ru.alastor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.RoleUser;

/**
 * Created on 23.09.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface RoleUserDao extends CrudRepository<RoleUser, Long> {
}
