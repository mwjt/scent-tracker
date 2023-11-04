package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.PerfumeDTO;
import pl.pwr.scenttracker.model.Perfume;

public class PerfumeMapper {
    public static PerfumeDTO toPerfumeDTO(Perfume perfume) {
        return PerfumeDTO.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .brandId(perfume.getBrand().getId())
                .perfumerId(perfume.getPerfumer().getId())
                .concentrationId(perfume.getConcentration().getId())
                .galleryPath(perfume.getGalleryPath())
                .year(perfume.getYear())
                .scent(perfume.getScent())
                .longevity(perfume.getLongevity())
                .sillage(perfume.getSillage())
                .bottle(perfume.getBottle())
                .value(perfume.getValue())
                .build();
    }
}
