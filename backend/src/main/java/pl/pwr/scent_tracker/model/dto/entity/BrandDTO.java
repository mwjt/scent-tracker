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
public class BrandDTO {
    private Long id;
    private String name;
    private Long galleryId;
    private String text;
    private String website;
    private Long countryId;
}
