package pl.pwr.scent_tracker.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.scent_tracker.model.dto.entity.CollectionEntryDTO;
import pl.pwr.scent_tracker.service.CollectionService;

@RestController
@RequestMapping(path = "/api/v1/collection/")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ResponseBody
    @GetMapping("/entry")
    public ResponseEntity getEntry(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "perfumeId") int perfumeId
    ) {
        try {
            CollectionEntryDTO entry = collectionService.getCollectionEntryByUserAndPerfume((long) userId, (long) perfumeId);
            return ResponseEntity.ok(entry);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/entry")
    public ResponseEntity changeEntry(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "perfumeId", required = false) String perfumeId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "quantity", required = false) String quantity,
            @RequestParam(value = "note", required = false) String note
    ) {
        try {
            CollectionEntryDTO entry = collectionService.saveOrUpdateEntry(Long.valueOf(userId), Long.valueOf(perfumeId), type, Short.parseShort(quantity), note);
            return ResponseEntity.ok(entry);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

}
