package ru.alastor.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.Application;

/**
 * Created on 18.09.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface ApplicationDao extends PagingAndSortingRepository<Application, Long> {

}
