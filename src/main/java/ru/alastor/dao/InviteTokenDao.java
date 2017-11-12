package ru.alastor.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.InviteToken;

/**
 * Created on 03.11.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface InviteTokenDao extends CrudRepository<InviteToken, Long> {
    InviteToken findByToken(String token);
}
