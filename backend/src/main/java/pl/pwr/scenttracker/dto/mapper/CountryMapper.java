package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.CountryDTO;
import pl.pwr.scenttracker.model.Country;

public class CountryMapper {
    public static CountryDTO toCountryDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
