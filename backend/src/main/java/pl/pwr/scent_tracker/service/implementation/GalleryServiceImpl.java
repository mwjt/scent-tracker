package pl.pwr.scent_tracker.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.entity.Gallery;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.repository.GalleryRepository;
import pl.pwr.scent_tracker.repository.PerfumeRepository;
import pl.pwr.scent_tracker.service.GalleryService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class GalleryServiceImpl implements GalleryService {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public Gallery saveImage(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery getImageById(Long id) throws Exception {
        Gallery gallery = galleryRepository.findById(id).orElse(null);
        if (gallery == null) throw new Exception("Image not found");
        return gallery;
    }

    @Override
    public Gallery savePerfumeImage(Long perfumeId, Gallery gallery) {
        return null;
    }

    @Override
    public Gallery saveProfileImage(Long userId, Gallery gallery) {
        return null;
    }

}
