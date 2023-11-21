package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.PerfumeDTO;
import pl.pwr.scent_tracker.model.entity.Perfume;

public class PerfumeMapper {
    public static PerfumeDTO toPerfumeDTO(Perfume perfume) {
        return PerfumeDTO.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .brandId(perfume.getBrand().getId())
                .perfumerId(perfume.getPerfumer().getId())
                .concentrationId(perfume.getConcentration().getId())
                .galleryId(perfume.getGallery().getId())
                .year(perfume.getYear())
                .scent(perfume.getScent())
                .longevity(perfume.getLongevity())
                .sillage(perfume.getSillage())
                .bottle(perfume.getBottle())
                .value(perfume.getValue())
                .build();
    }
}
