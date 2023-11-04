package pl.pwr.scenttracker.dto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionEntryDTO {

    private Long id;
    private Long userId;
    private Long perfumeId;
    private Long collectionTypeId;
    private Short quantity;
    private Blob note;
}
