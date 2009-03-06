package se.citerus.dddsample.infrastructure.persistence.hibernate;

import org.springframework.stereotype.Repository;
import se.citerus.dddsample.domain.model.cargo.TrackingId;
import se.citerus.dddsample.domain.model.handling.HandlingEvent;
import se.citerus.dddsample.domain.model.handling.HandlingEventRepository;

import java.util.List;

/**
 * Hibernate implementation of HandlingEventRepository.
 *
 */
@Repository
public class HandlingEventRepositoryHibernate extends HibernateRepository implements HandlingEventRepository {

  @Override
  public void store(HandlingEvent event) {
    getSession().save(event);
  }

  @Override
  public List<HandlingEvent> findEventsForCargo(TrackingId tid) {
    return getSession().createQuery(
            "from HandlingEvent where cargo.trackingId = :tid").
            setParameter("tid", tid).
            list();
  }

}