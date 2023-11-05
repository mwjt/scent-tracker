package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.CollectionEntryDTO;
import pl.pwr.scent_tracker.model.entity.CollectionEntry;

public class CollectionEntryMapper {
    public static CollectionEntryDTO toCollectionEntryDTO(CollectionEntry collectionEntry) {
        return CollectionEntryDTO.builder()
                .id(collectionEntry.getId())
                .userId(collectionEntry.getUser().getId())
                .perfumeId(collectionEntry.getPerfume().getId())
                .collectionTypeId(collectionEntry.getCollectionType().getId())
                .quantity(collectionEntry.getQuantity())
                .note(collectionEntry.getNote())
                .build();
    }
}
