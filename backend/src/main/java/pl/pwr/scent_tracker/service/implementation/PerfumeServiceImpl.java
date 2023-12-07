package pl.pwr.scent_tracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.api.PagePerfumeSimpleRes;
import pl.pwr.scent_tracker.model.api.PerfumeReq;
import pl.pwr.scent_tracker.model.api.PerfumeSimpleRes;
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

    @Autowired
    private GalleryRepository galleryRepository;

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
        if (accord == null) throw new Exception("Accord does not exist");
        accord = accordRepository.findByName(accordDTO.getName());
        if (!Objects.equals(accord.getId(), accordDTO.getId())) throw new Exception("Accord does not exist");
        accord = accordRepository.save(accord.setName(accordDTO.getName()));
        return AccordMapper.toAccordDTO(accord);
    }

    @Override
    public void removeAccord(Long id) throws Exception {
        Accord accord = accordRepository.findById(id).orElse(null);
        if (accord == null) throw new Exception("Accord does not exist");
        accordRepository.delete(accord);
    }

    @Override
    public BrandDTO getBrand(String name) {
        return BrandMapper.toBrandDTO(brandRepository.findByName(name));
    }

    @Override
    public BrandDTO addBrand(BrandDTO brandDTO) throws Exception {
        Brand brand = brandRepository.findByName(brandDTO.getName());
        if (brand != null) throw new Exception("Brand already exists");

        Gallery gallery = galleryRepository.findByName("Default brand");
        brand = brandRepository.save(Brand.builder()
                .name(brandDTO.getName())
                .website(brandDTO.getWebsite())
                .gallery(gallery)
                .text(brandDTO.getText())
                .build());
        return BrandMapper.toBrandDTO(brand);
    }

    @Override
    public BrandDTO updateBrand(BrandDTO brandDTO) throws Exception {
        Brand brand = brandRepository.findById(brandDTO.getId()).orElse(null);
        if (brand == null) throw new Exception("Brand does not exist");
        brand = brandRepository.findByName(brandDTO.getName());
        if (!Objects.equals(brand.getId(), brandDTO.getId())) throw new Exception("Brand already exists");
        brand = brandRepository.save(brand
                .setName(brandDTO.getName())
                .setWebsite(brandDTO.getWebsite())
                .setGallery(galleryRepository.findById(brandDTO.getGalleryId()).orElse(null))
                .setText(brandDTO.getText()));
        return BrandMapper.toBrandDTO(brand);
    }

    @Override
    public void removeBrand(Long id) throws Exception {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) throw new Exception("Brand does not exist");
        brandRepository.delete(brand);
    }

    @Override
    public Brand getBrandById(Long id) throws Exception {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public BrandDTO setBrandPhoto(Brand brand, Gallery gallery) {
        brand.setGallery(gallery);
        return BrandMapper.toBrandDTO(brand);
    }

    @Override
    public Perfume getPerfume(PerfumeReq perfumeReq) {
        Brand brand = brandRepository.findByName(perfumeReq.getBrand());
        return perfumeRepository.findByNameAndBrand(perfumeReq.getName(), brand);
    }

    @Override
    public PerfumeDTO addPerfume(PerfumeDTO perfumeDTO) throws Exception {
        Brand brand = brandRepository.findById(perfumeDTO.getBrandId()).orElse(null);
        if (brand == null) throw new Exception("Brand does not exist");
        Perfumer perfumer = perfumerRepository.findById(perfumeDTO.getPerfumerId()).orElse(null);
        if (perfumer == null) throw new Exception("Perfumer does not exist");
        Concentration concentration = concentrationRepository.findById(perfumeDTO.getConcentrationId()).orElse(null);
        if (concentration == null) throw new Exception("Concentration does not exist");
        Perfume perfume = perfumeRepository.findByNameAndBrand(perfumeDTO.getName(), brand);
        if (perfume != null) throw new Exception("Perfume already exist");

        Gallery gallery = galleryRepository.findByName("Default perfume");

        perfume = Perfume.builder()
                .name(perfumeDTO.getName())
                .brand(brand)
                .perfumer(perfumer)
                .concentration(concentration)
                .year(perfumeDTO.getYear())
                .bottle(0.0f)
                .scent(0.0f)
                .sillage(0.0f)
                .longevity(0.0f)
                .value(0.0f)
                .gallery(gallery)
                .build();
        perfume = perfumeRepository.save(perfume);
        return PerfumeMapper.toPerfumeDTO(perfume);
    }

    @Override
    public PerfumeDTO updatePerfume(PerfumeDTO perfumeDTO) throws Exception {
        Brand brand = brandRepository.findById(perfumeDTO.getBrandId()).orElse(null);
        if (brand == null) throw new Exception("Brand does not exist");
        Perfumer perfumer = perfumerRepository.findById(perfumeDTO.getPerfumerId()).orElse(null);
        if (perfumer == null) throw new Exception("Perfumer does not exist");
        Concentration concentration = concentrationRepository.findById(perfumeDTO.getConcentrationId()).orElse(null);
        if (concentration == null) throw new Exception("Concentration does not exist");
        Perfume perfume = perfumeRepository.findByNameAndBrand(perfumeDTO.getName(), brand);
        if (perfume != null && !Objects.equals(perfume.getId(), perfumeDTO.getId())) throw new Exception("Perfume already exist");
        perfume = perfumeRepository.findById(perfumeDTO.getId()).orElse(null);
        if (perfume == null) throw new Exception("Perfume does not exist");

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
        if (perfume == null) throw new Exception("Perfume does not exist");
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
        if (perfume == null) throw new Exception("Perfume does not exist");
        perfumeRepository.delete(perfume);
    }

    @Override
    public PagePerfumeSimpleRes getPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Perfume> perfumePage = perfumeRepository.findAll(pageable);
        List<Perfume> perfumeList = perfumePage.getContent();
        List<PerfumeSimpleRes> perfumes = perfumeList.stream().map(p -> new PerfumeSimpleRes(p.getId(), p.getBrand().getName(), p.getName(), p.getGallery().getId())).toList();
        int total = perfumePage.getTotalPages();
        return new PagePerfumeSimpleRes(total, perfumes);
    }

    @Override
    public PerfumeSimpleRes getSimplePerfumeById(Long id) throws Exception {
        Perfume perfume = perfumeRepository.findById(id).orElse(null);
        if (perfume == null) throw new Exception("Perfume not found");
        return new PerfumeSimpleRes(perfume.getId(), perfume.getBrand().getName(), perfume.getName(), perfume.getGallery().getId());
    }

    @Override
    public Perfume getPerfumeById(Long id) {
        return perfumeRepository.findById(id).orElse(null);
    }

    @Override
    public PerfumeDTO setPerfumePhoto(Perfume perfume, Gallery gallery) {
        perfume.setGallery(gallery);
        return PerfumeMapper.toPerfumeDTO(perfume);
    }
}
