package ru.alastor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alastor.domain.ApplicationHistory;

import java.util.List;

/**
 * Created on 30.11.17.
 *
 * @author Maxim Goncharov
 */
@Repository
public interface ApplicationHistoryDao extends CrudRepository<ApplicationHistory, Long> {
    List<ApplicationHistory> getApplicationHistoriesByUserSignerId(Long userId);
}
