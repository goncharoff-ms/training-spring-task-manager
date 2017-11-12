package ru.alastor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.Token;

/**
 * Created on 23.10.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface TokenDao extends CrudRepository<Token, Long> {
    Token getTokenByOwnerId(Long id);
}
