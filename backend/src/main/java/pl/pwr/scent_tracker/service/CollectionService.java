package pl.pwr.scent_tracker.service;

import pl.pwr.scent_tracker.model.dto.entity.CollectionEntryDTO;

public interface CollectionService {

    public CollectionEntryDTO getCollectionEntryByUserAndPerfume(Long userId, Long perfumeId) throws Exception;
    public CollectionEntryDTO getCollectionEntry(Long id) throws Exception;
    public CollectionEntryDTO saveCollectionEntry(CollectionEntryDTO entry) throws Exception;
    public CollectionEntryDTO updateCollectionEntry(CollectionEntryDTO oldEntry, CollectionEntryDTO newEntry) throws Exception;
    public CollectionEntryDTO saveOrUpdateEntry(Long userId, Long perfumeId, String type, int quantity, String note) throws Exception;
}
