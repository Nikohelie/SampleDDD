package se.citerus.dddsample.tracking.core.application.booking;

import se.citerus.dddsample.tracking.core.domain.model.cargo.Cargo;
import se.citerus.dddsample.tracking.core.domain.model.cargo.Itinerary;
import se.citerus.dddsample.tracking.core.domain.model.cargo.TrackingId;
import se.citerus.dddsample.tracking.core.domain.model.location.UnLocode;

import java.util.Date;
import java.util.List;

/**
 * Cargo booking service.
 */
public interface BookingService {

  /**
   * Registers a new cargo in the tracking system, not yet routed.
   *
   * @param origin          cargo origin
   * @param destination     cargo destination
   * @param arrivalDeadline arrival deadline
   * @return Cargo tracking id
   */
  TrackingId bookNewCargo(UnLocode origin, UnLocode destination, Date arrivalDeadline);

  /**
   * Requests a list of itineraries describing possible routes for this cargo.
   *
   * @param trackingId cargo tracking id
   * @return A list of possible itineraries for this cargo
   */
  List<Itinerary> requestPossibleRoutesForCargo(TrackingId trackingId);

  /**
   * @param itinerary  itinerary describing the selected route
   * @param trackingId cargo tracking id
   */
  void assignCargoToRoute(Itinerary itinerary, TrackingId trackingId);

  /**
   * Changes the destination of a cargo.
   *
   * @param trackingId cargo tracking id
   * @param unLocode   UN locode of new destination
   */
  void changeDestination(TrackingId trackingId, UnLocode unLocode);

  /**
   * Loads a cargo for (re-) routing.
   * Locks the cargo for exclusive modification.
   *
   * @param trackingId tracking id
   * @return The cargo.
   */
  Cargo loadCargoForRouting(TrackingId trackingId);

}
