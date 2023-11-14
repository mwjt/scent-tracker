package pl.pwr.scent_tracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.api.PerfumeReq;
import pl.pwr.scent_tracker.model.dto.entity.AccordDTO;
import pl.pwr.scent_tracker.model.dto.entity.BrandDTO;
import pl.pwr.scent_tracker.model.dto.entity.PerfumeDTO;
import pl.pwr.scent_tracker.model.dto.mapper.AccordMapper;
import pl.pwr.scent_tracker.model.dto.mapper.BrandMapper;
import pl.pwr.scent_tracker.model.dto.mapper.PerfumeMapper;
import pl.pwr.scent_tracker.model.entity.*;
import pl.pwr.scent_tracker.repository.*;
import pl.pwr.scent_tracker.service.PerfumeService;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Component
public class PerfumeServiceImpl implements PerfumeService {

    @Autowired
    private AccordRepository accordRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Autowired
    private PerfumerRepository perfumerRepository;

    @Autowired
    private ConcentrationRepository concentrationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public AccordDTO getAccord(String name) {
        return AccordMapper.toAccordDTO(accordRepository.findByName(name));
    }

    @Override
    public AccordDTO addAccord(AccordDTO accordDTO) throws Exception {
        Accord accord = accordRepository.findByName(accordDTO.getName());
        if (accord != null) throw new Exception(accordDTO.getName() + " already exists");
        accord = accordRepository.save(Accord.builder().name(accordDTO.getName()).build());
        return AccordMapper.toAccordDTO(accord);
    }

    @Override
    public AccordDTO updateAccord(AccordDTO accordDTO) throws Exception {
        Accord accord = accordRepository.findById(accordDTO.getId()).orElse(null);
        if (accord == null) throw new Exception("Accord with id " + accordDTO.getId() + " does not exist");
        accord = accordRepository.findByName(accordDTO.getName());
        if (!Objects.equals(accord.getId(), accordDTO.getId())) throw new Exception(accordDTO.getName() + " does not exist");
        accord = accordRepository.save(accord.setName(accordDTO.getName()));
        return AccordMapper.toAccordDTO(accord);
    }

    @Override
    public void removeAccord(Long id) throws Exception {
        Accord accord = accordRepository.findById(id).orElse(null);
        if (accord == null) throw new Exception("Accord with id " + id + " does not exist");
        accordRepository.delete(accord);
    }

    @Override
    public BrandDTO getBrand(String name) {
        return BrandMapper.toBrandDTO(brandRepository.findByName(name));
    }

    @Override
    public BrandDTO addBrand(BrandDTO brandDTO) throws Exception {
        Brand brand = brandRepository.findByName(brandDTO.getName());
        if (brand != null) throw new Exception(brandDTO.getName() + " already exists");
        brand = brandRepository.save(Brand.builder()
                .name(brandDTO.getName())
                .website(brandDTO.getWebsite())
                .photoPath(brandDTO.getPhotoPath())
                .textPath(brandDTO.getTextPath())
                .build());
        return BrandMapper.toBrandDTO(brand);
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO) throws Exception {
        Brand brand = brandRepository.findById(brandDTO.getId()).orElse(null);
        if (brand == null) throw new Exception("Brand with id " + brandDTO.getId() + " does not exist");
        brand = brandRepository.findByName(brandDTO.getName());
        if (!Objects.equals(brand.getId(), brandDTO.getId())) throw new Exception("Brand" + brandDTO.getName() + " already exists");
        brand = brandRepository.save(brand
                .setName(brandDTO.getName())
                .setWebsite(brandDTO.getWebsite())
                .setPhotoPath(brandDTO.getPhotoPath())
                .setTextPath(brandDTO.getTextPath()));
        return BrandMapper.toBrandDTO(brand);
    }

    @Override
    public void removeBrand(Long id) throws Exception {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) throw new Exception("Brand with id " + id + " does not exist");
        brandRepository.delete(brand);
    }

    @Override
    public PerfumeDTO getPerfume(PerfumeReq perfumeReq) {
        Brand brand = brandRepository.findByName(perfumeReq.getBrand());
        Perfume perfume = perfumeRepository.findByNameAndBrand(perfumeReq.getName(), brand);
        return PerfumeMapper.toPerfumeDTO(perfume);
    }

    @Override
    public PerfumeDTO addPerfume(PerfumeDTO perfumeDTO) throws Exception {
        Brand brand = brandRepository.findById(perfumeDTO.getBrandId()).orElse(null);
        if (brand == null) throw new Exception("Brand with id " + perfumeDTO.getBrandId() + " does not exist");
        Perfumer perfumer = perfumerRepository.findById(perfumeDTO.getPerfumerId()).orElse(null);
        if (perfumer == null) throw new Exception("Perfumer with id " + perfumeDTO.getPerfumerId() + " does not exist");
        Concentration concentration = concentrationRepository.findById(perfumeDTO.getConcentrationId()).orElse(null);
        if (concentration == null) throw new Exception("Concentration with id " + perfumeDTO.getConcentrationId() + " does not exist");
        Perfume perfume = perfumeRepository.findByNameAndBrand(perfumeDTO.getName(), brand);
        if (perfume != null) throw new Exception("Perfume " + perfumeDTO.getName() + " of brand " + brand.getName() + " already exist");

        perfume = Perfume.builder()
                .name(perfumeDTO.getName())
                .brand(brand)
                .perfumer(perfumer)
                .concentration(concentration)
                .galleryPath("")
                //.galleryPath()
                .year(perfumeDTO.getYear())
                .bottle(0.0f)
                .scent(0.0f)
                .sillage(0.0f)
                .longevity(0.0f)
                .value(0.0f)
                .build();
        perfume = perfumeRepository.save(perfume);
        return PerfumeMapper.toPerfumeDTO(perfume);
    }

    @Override
    public PerfumeDTO updatePerfume(PerfumeDTO perfumeDTO) throws Exception {
        Brand brand = brandRepository.findById(perfumeDTO.getBrandId()).orElse(null);
        if (brand == null) throw new Exception("Brand with id " + perfumeDTO.getBrandId() + " does not exist");
        Perfumer perfumer = perfumerRepository.findById(perfumeDTO.getPerfumerId()).orElse(null);
        if (perfumer == null) throw new Exception("Perfumer with id " + perfumeDTO.getPerfumerId() + " does not exist");
        Concentration concentration = concentrationRepository.findById(perfumeDTO.getConcentrationId()).orElse(null);
        if (concentration == null) throw new Exception("Concentration with id " + perfumeDTO.getConcentrationId() + " does not exist");
        Perfume perfume = perfumeRepository.findByNameAndBrand(perfumeDTO.getName(), brand);
        if (perfume != null && !Objects.equals(perfume.getId(), perfumeDTO.getId())) throw new Exception("Perfume " + perfumeDTO.getName() + " of brand " + brand.getName() + " already exist");
        perfume = perfumeRepository.findById(perfumeDTO.getId()).orElse(null);
        if (perfume == null) throw new Exception("Perfume with id " + perfumeDTO.getId() + " does not exist");

        perfume.setName(perfumeDTO.getName());
        perfume.setBrand(brand);
        perfume.setPerfumer(perfumer);
        perfume.setConcentration(concentration);

        perfume = perfumeRepository.save(perfume);
        return PerfumeMapper.toPerfumeDTO(perfume);
    }

    @Override
    public void updateScores(Long id) throws Exception {
        Perfume perfume = perfumeRepository.findById(id).orElse(null);
        if (perfume == null) throw new Exception("Perfume with id " + id + " does not exist");
        List<Review> reviews = reviewRepository.findByPerfume(perfume);

        BigInteger scent = BigInteger.ZERO;
        BigInteger longevity = BigInteger.ZERO;
        BigInteger sillage = BigInteger.ZERO;
        BigInteger bottle = BigInteger.ZERO;
        BigInteger value = BigInteger.ZERO;

        int sceCount = 0;
        int lonCount = 0;
        int silCount = 0;
        int botCount = 0;
        int valCount = 0;

        for (Review r : reviews) {
            if (r.getScent() != 0) {
                scent = scent.add(BigInteger.valueOf(r.getScent()));
                sceCount++;
            }
            if (r.getLongevity() != 0) {
                longevity = longevity.add(BigInteger.valueOf(r.getLongevity()));
                lonCount++;
            }
            if (r.getSillage() != 0) {
                sillage = sillage.add(BigInteger.valueOf(r.getSillage()));
                silCount++;
            }
            if (r.getBottle() != 0) {
                bottle = bottle.add(BigInteger.valueOf(r.getBottle()));
                botCount++;
            }
            if (r.getValue() != 0) {
                value = value.add(BigInteger.valueOf(r.getValue()));
                valCount++;
            }
        }

        perfume.setScent(scent.floatValue() / (float) sceCount);
        perfume.setLongevity(longevity.floatValue() / (float) lonCount);
        perfume.setSillage(sillage.floatValue() / (float) silCount);
        perfume.setBottle(bottle.floatValue() / (float) botCount);
        perfume.setValue(value.floatValue() / (float) valCount);

        perfumeRepository.save(perfume);
    }


    @Override
    public void removePerfume(Long id) throws Exception {
        Perfume perfume = perfumeRepository.findById(id).orElse(null);
        if (perfume == null) throw new Exception("Perfume with id " + id + " does not exist");
        perfumeRepository.delete(perfume);
    }
}