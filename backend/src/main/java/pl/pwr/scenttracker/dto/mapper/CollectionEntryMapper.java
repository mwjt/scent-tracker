package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.CollectionEntryDTO;
import pl.pwr.scenttracker.model.CollectionEntry;

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
