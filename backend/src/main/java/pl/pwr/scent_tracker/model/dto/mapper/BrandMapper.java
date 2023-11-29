package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.BrandDTO;
import pl.pwr.scent_tracker.model.entity.Brand;

public class BrandMapper {
    public static BrandDTO toBrandDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .galleryId(brand.getGallery().getId())
                .text(brand.getText())
                .website(brand.getWebsite())
                .countryId(brand.getCountry().getId())
                .build();
    }
}
