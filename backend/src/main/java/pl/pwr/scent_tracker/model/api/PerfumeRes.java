package pl.pwr.scent_tracker.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeRes {
    private String id;
    private String brand;
    private String name;
    private String perfumer;
    private Long galleryId;
    private String concentration;
    private String year;
    private String scent;
    private String longevity;
    private String sillage;
    private String bottle;
    private String value;
    private List<String> tags;
}
