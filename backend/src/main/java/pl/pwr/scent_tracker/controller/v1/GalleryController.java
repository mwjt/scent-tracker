package pl.pwr.scent_tracker.controller.v1;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.pwr.scent_tracker.model.entity.Brand;
import pl.pwr.scent_tracker.model.entity.Gallery;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.service.GalleryService;
import pl.pwr.scent_tracker.service.PerfumeService;
import pl.pwr.scent_tracker.service.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(path = "/api/v1/gallery/")
public class GalleryController {

    @Value("${uploadDir}")
    private String uploadFolder;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private PerfumeService perfumeService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping
    public ResponseEntity getImage(@RequestParam(value = "id") String id) {
        try {
            Gallery gallery = galleryService.getImageById(Long.valueOf(id));
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(gallery.getImage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @ResponseBody
    @PostMapping
    public ResponseEntity addImage(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "entityType") String entityType,
            @RequestParam(value = "entityId") String entityId,
            final @RequestParam("image")MultipartFile file) {

        List<String> supportedEntities = new ArrayList<>(Arrays.asList("perfume", "brand", "perfumer", "profile"));
        if (supportedEntities.contains(entityType)) {
            try {
                if (perfumeService.getPerfumeById(Long.valueOf(entityId)) == null)
                    return ResponseEntity.badRequest().body("ID " + entityId + " of " + entityType + " not found");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(e.getMessage());
            }
        } else return ResponseEntity.badRequest().body(entityType + " does not support images");

        try {
            String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            if (fileName == null || fileName.contains("..")) return ResponseEntity.badRequest().body("Bad filename");
            Date createDate = new Date();
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) dir.mkdirs();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
                bos.write(file.getBytes());
                bos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.internalServerError().body(ex.getMessage());
            }
            byte[] imageData = file.getBytes();
            Gallery gallery = Gallery.builder()
                    .createDate(createDate)
                    .image(imageData)
                    .name(name)
                    .build();

            Gallery savedGallery = galleryService.saveImage(gallery);

            if (entityType.equalsIgnoreCase("perfume")) {
                Perfume perfume = perfumeService.getPerfumeById(Long.valueOf(entityId));
                perfumeService.setPerfumePhoto(perfume, savedGallery);
            } else if (entityType.equalsIgnoreCase("brand")) {
                Brand brand = perfumeService.getBrandById(Long.valueOf(entityId));
                perfumeService.setBrandPhoto(brand, savedGallery);
            } else if (entityType.equalsIgnoreCase("profile")) {
                User user = userService.getUserById(Long.valueOf(entityId));
                userService.setUserPhoto(user, savedGallery);
            } else if (entityType.equalsIgnoreCase("perfumer")) {
                // TODO
            }
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
