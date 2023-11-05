package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.PerfumerDTO;
import pl.pwr.scent_tracker.model.entity.Perfumer;

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
