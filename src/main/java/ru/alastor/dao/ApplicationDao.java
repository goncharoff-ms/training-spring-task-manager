package ru.alastor.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.Application;

import java.util.List;

/**
 * Created on 18.09.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface ApplicationDao extends PagingAndSortingRepository<Application, Long> {
    List<Application> getByUserId(Long userId);
    List<Application> findByNameStartingWith(String username);
}
