package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.PerfumerDTO;
import pl.pwr.scenttracker.model.Perfumer;

public class PerfumerMapper {
    public static PerfumerDTO toPerfumerDTO(Perfumer perfumer) {
        return PerfumerDTO.builder()
                .id(perfumer.getId())
                .name(perfumer.getName())
                .photoPath(perfumer.getPhotoPath())
                .textPath(perfumer.getTextPath())
                .website(perfumer.getWebsite())
                .countryId(perfumer.getCountry().getId())
                .build();
    }
}
