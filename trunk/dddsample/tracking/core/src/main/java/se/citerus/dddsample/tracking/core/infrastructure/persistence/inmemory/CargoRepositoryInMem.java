package se.citerus.dddsample.tracking.core.infrastructure.persistence.inmemory;

import se.citerus.dddsample.tracking.core.domain.model.cargo.Cargo;
import se.citerus.dddsample.tracking.core.domain.model.cargo.CargoRepository;
import se.citerus.dddsample.tracking.core.domain.model.cargo.RouteSpecification;
import se.citerus.dddsample.tracking.core.domain.model.cargo.TrackingId;
import se.citerus.dddsample.tracking.core.domain.model.location.Location;
import static se.citerus.dddsample.tracking.core.domain.model.location.SampleLocations.*;
import se.citerus.dddsample.tracking.core.domain.model.voyage.Voyage;

import java.util.*;

/**
 * CargoRepositoryInMem implement the CargoRepository interface but is a test
 * class not intended for usage in real application.
 * <p/>
 * It setup a simple local hash with a number of Cargo's with TrackingId as key
 * defined at compile time.
 * <p/>
 */
public class CargoRepositoryInMem implements CargoRepository {

  private Map<String, Cargo> cargoDb;

  /**
   * Constructor.
   */
  public CargoRepositoryInMem() {
    cargoDb = new HashMap<String, Cargo>();
  }

  public Cargo find(final TrackingId trackingId) {
    return cargoDb.get(trackingId.stringValue());
  }

  @Override
  public List<Cargo> findCargosOnVoyage(Voyage voyage) {
    List<Cargo> onVoyage = new ArrayList<Cargo>();
    for (Cargo cargo : cargoDb.values()) {
      if (voyage.sameAs(cargo.currentVoyage())) {
        onVoyage.add(cargo);
      }
    }
    return onVoyage;
  }

  @Override
  public void store(Cargo cargo) {
    cargoDb.put(cargo.trackingId().stringValue(), cargo);
  }

  @Override
  public List<Cargo> findAll() {
    return new ArrayList<Cargo>(cargoDb.values());
  }

  public void init() throws Exception {
    final TrackingId xyz = new TrackingId("XYZ");
    final Cargo cargoXYZ = createCargoWithDeliveryHistory(xyz, STOCKHOLM, MELBOURNE);
    cargoDb.put(xyz.stringValue(), cargoXYZ);

    final TrackingId zyx = new TrackingId("ZYX");
    final Cargo cargoZYX = createCargoWithDeliveryHistory(zyx, MELBOURNE, STOCKHOLM);
    cargoDb.put(zyx.stringValue(), cargoZYX);

    final TrackingId abc = new TrackingId("ABC");
    final Cargo cargoABC = createCargoWithDeliveryHistory(abc, STOCKHOLM, HELSINKI);
    cargoDb.put(abc.stringValue(), cargoABC);

    final TrackingId cba = new TrackingId("CBA");
    final Cargo cargoCBA = createCargoWithDeliveryHistory(cba, HELSINKI, STOCKHOLM);
    cargoDb.put(cba.stringValue(), cargoCBA);
  }

  public static Cargo createCargoWithDeliveryHistory(TrackingId trackingId,
                                                     Location origin,
                                                     Location destination) {

    final RouteSpecification routeSpecification = new RouteSpecification(origin, destination, new Date());
    final Cargo cargo = new Cargo(trackingId, routeSpecification);

    return cargo;
  }
}
