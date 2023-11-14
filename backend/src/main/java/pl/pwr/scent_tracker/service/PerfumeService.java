package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.api.PerfumeReq;
import pl.pwr.scent_tracker.model.dto.entity.AccordDTO;
import pl.pwr.scent_tracker.model.dto.entity.BrandDTO;
import pl.pwr.scent_tracker.model.dto.entity.PerfumeDTO;

public interface PerfumeService {

    // Accords
    AccordDTO getAccord(String name);
    AccordDTO addAccord(AccordDTO accordDTO) throws Exception;
    AccordDTO updateAccord(AccordDTO accordDTO) throws Exception;
    void removeAccord(Long id) throws Exception;

    // Brands
    BrandDTO getBrand(String name);
    BrandDTO addBrand(BrandDTO brandDTO) throws Exception;
    BrandDTO updateBrand(BrandDTO brandDTO) throws Exception;
    void removeBrand(Long id) throws Exception;

    // Perfumes
    PerfumeDTO getPerfume(PerfumeReq perfumeReq);
    PerfumeDTO addPerfume(PerfumeDTO perfumeDTO) throws Exception;
    PerfumeDTO updatePerfume(PerfumeDTO perfumeDTO) throws Exception;
    void updateScores(Long id) throws Exception;
    void removePerfume(Long id) throws Exception;

}
