package pl.pwr.scenttracker.service;

import pl.pwr.scenttracker.dto.entity.*;

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
