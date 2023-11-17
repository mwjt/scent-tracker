package pl.pwr.scent_tracker.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.api.PagePerfumeSimpleRes;
import pl.pwr.scent_tracker.model.api.PerfumeReq;
import pl.pwr.scent_tracker.model.api.PerfumeSimpleRes;
import pl.pwr.scent_tracker.model.dto.entity.PerfumeDTO;
import pl.pwr.scent_tracker.model.dto.mapper.PerfumeMapper;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.repository.PerfumeRepository;
import pl.pwr.scent_tracker.service.PerfumeService;
import pl.pwr.scent_tracker.utils.AppConstants;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        PerfumeDTO perfume = perfumeService.getPerfume(new PerfumeReq(brand.replace('_', ' '), name.replace('_', ' ')));
        if (perfume == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(perfume);
    }

    @ResponseBody
    @GetMapping(value = "/id")
    public ResponseEntity getById(@RequestParam(value = "id") String id) {
        try {
            PerfumeSimpleRes perfume = perfumeService.getPerfumeById(Long.valueOf(id));
            return ResponseEntity.ok(perfume);
        } catch (Exception e) {
            if (e.getMessage().equals("404")) return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(null);
    }
}
