package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.CountryDTO;
import pl.pwr.scent_tracker.model.entity.Country;

public class CountryMapper {
    public static CountryDTO toCountryDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}
