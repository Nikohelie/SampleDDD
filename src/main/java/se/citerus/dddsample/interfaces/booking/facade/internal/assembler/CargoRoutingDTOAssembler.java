package se.citerus.dddsample.interfaces.booking.facade.internal.assembler;

import se.citerus.dddsample.domain.model.cargo.Cargo;
import se.citerus.dddsample.domain.model.cargo.RoutingStatus;
import se.citerus.dddsample.interfaces.booking.facade.dto.CargoRoutingDTO;

/**
 * Assembler class for the CargoRoutingDTO.
 */
public class CargoRoutingDTOAssembler {

    /**
     * @param cargo cargo
     * @return A cargo routing DTO
     */
    public CargoRoutingDTO toDTO(final Cargo cargo) {
        final CargoRoutingDTO dto = new CargoRoutingDTO(
                cargo.trackingId().idString(),
                cargo.origin().unLocode().idString(),
                cargo.routeSpecification().destination().unLocode().idString(),
                cargo.routeSpecification().arrivalDeadline(),
                cargo.delivery().routingStatus().sameValueAs(RoutingStatus.MISROUTED));

        cargo.itinerary().legs()
                .stream()
                .forEach(leg -> dto.addLeg(
                        leg.voyage().voyageNumber().idString(),
                        leg.loadLocation().unLocode().idString(),
                        leg.unloadLocation().unLocode().idString(),
                        leg.loadTime(),
                        leg.unloadTime()));
        return dto;
    }

}
