package se.citerus.dddsample.interfaces.booking.facade.internal.assembler;

import se.citerus.dddsample.domain.model.location.Location;
import se.citerus.dddsample.interfaces.booking.facade.dto.LocationDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LocationDTOAssembler {

    public LocationDTO toDTO(Location location) {
        return new LocationDTO(location.unLocode().idString(), location.name());
    }

    public List<LocationDTO> toDTOList(List<Location> allLocations) {
        return allLocations
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
