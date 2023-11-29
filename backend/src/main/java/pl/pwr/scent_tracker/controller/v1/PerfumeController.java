package pl.pwr.scent_tracker.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.api.PagePerfumeSimpleRes;
import pl.pwr.scent_tracker.model.api.PerfumeReq;
import pl.pwr.scent_tracker.model.api.PerfumeRes;
import pl.pwr.scent_tracker.model.api.PerfumeSimpleRes;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.Tag;
import pl.pwr.scent_tracker.service.PerfumeService;
import pl.pwr.scent_tracker.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/perfume/")
public class PerfumeController {
    //TODO


    @Autowired
    private PerfumeService perfumeService;

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity getAll(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PagePerfumeSimpleRes page = perfumeService.getPage(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(page);
    }

    @ResponseBody
    @GetMapping("/brandAndName")
    public ResponseEntity get(@RequestParam(value = "brand") String brand, @RequestParam(value = "name") String name) {
        Perfume perfume = perfumeService.getPerfume(new PerfumeReq(brand.replace('_', ' '), name.replace('_', ' ')));
        if (perfume == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(
                PerfumeRes.builder()
                        .id(String.valueOf(perfume.getId()))
                        .name(perfume.getName())
                        .brand(perfume.getBrand().getName())
                        .perfumer(perfume.getPerfumer().getName())
                        .galleryId(perfume.getGallery().getId())
                        .concentration(perfume.getConcentration().getName())
                        .year(String.valueOf(perfume.getYear()))
                        .scent(String.valueOf(perfume.getScent()))
                        .longevity(String.valueOf(perfume.getLongevity()))
                        .sillage(String.valueOf(perfume.getSillage()))
                        .bottle(String.valueOf(perfume.getBottle()))
                        .value(String.valueOf(perfume.getValue()))
                        .tags(perfume.getTags().stream().map(Tag::getName).toList())
                .build());
    }

    @ResponseBody
    @GetMapping(value = "/id")
    public ResponseEntity getById(@RequestParam(value = "id") String id) {
        try {
            PerfumeSimpleRes perfume = perfumeService.getSimplePerfumeById(Long.valueOf(id));
            return ResponseEntity.ok(perfume);
        } catch (Exception e) {
            if (e.getMessage().equals("404")) return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(null);
    }
}
