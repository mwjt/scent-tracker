package pl.pwr.scent_tracker.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfumeSimpleRes {
    private Long id;
    private String brand;
    private String name;
    private Long galleryId;
}
