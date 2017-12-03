package ru.alastor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.ApplicationSign;
import ru.alastor.domain.RoleUser;

import java.util.List;

/**
 * Created on 30.11.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface ApplicationSignDao extends CrudRepository<ApplicationSign, Long> {
    List<ApplicationSign> getApplicationSignByApplicationId(Long appId);
    List<ApplicationSign> getApplicationSignByUserRole(RoleUser userRole);
}
