package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.dto.entity.AccordDTO;
import pl.pwr.scent_tracker.model.dto.entity.BrandDTO;

public interface PerfumeService {

    // Accords
    AccordDTO getAccord(AccordDTO accordDTO);
    AccordDTO addAccord(AccordDTO accordDTO);
    AccordDTO updateAccord(AccordDTO accordDTO);

    // Brands
    BrandDTO getBrand(BrandDTO brandDTO);
    BrandDTO addBrand(BrandDTO brandDTO);
    BrandDTO updateBrand(BrandDTO brandDTO);



}
