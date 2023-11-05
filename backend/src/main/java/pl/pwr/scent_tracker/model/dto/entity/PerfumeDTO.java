package pl.pwr.scent_tracker.model.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerfumeDTO {

    private Long id;
    private String name;
    private Long brandId;
    private Long perfumerId;
    private Long concentrationId;
    private String galleryPath;
    private Short year;
    private Float scent;
    private Float longevity;
    private Float sillage;
    private Float bottle;
    private Float value;
}
