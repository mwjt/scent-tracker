package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.entity.Gallery;

public interface GalleryService {

    Gallery saveImage(Gallery gallery);
    Gallery getImageById(Long id) throws Exception;
    Gallery savePerfumeImage(Long perfumeId, Gallery gallery);
    Gallery saveProfileImage(Long userId, Gallery gallery);
}
