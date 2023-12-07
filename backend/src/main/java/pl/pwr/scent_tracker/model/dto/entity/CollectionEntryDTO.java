package pl.pwr.scent_tracker.model.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import pl.pwr.scent_tracker.model.entity.CollectionType;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionEntryDTO {

    private Long id;
    private Long userId;
    private Long perfumeId;
    private CollectionType collectionType;
    private Short quantity;
    private String note;
}
