package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.BrandDTO;
import pl.pwr.scenttracker.model.Brand;

public class BrandMapper {
    public static BrandDTO toBrandDTO(Brand brand) {
        return BrandDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .photoPath(brand.getPhotoPath())
                .textPath(brand.getTextPath())
                .website(brand.getWebsite())
                .countryId(brand.getCountry().getId())
                .build();
    }
}
