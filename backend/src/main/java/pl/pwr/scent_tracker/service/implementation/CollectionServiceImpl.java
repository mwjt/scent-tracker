package pl.pwr.scent_tracker.service.implementation;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pwr.scent_tracker.model.dto.entity.CollectionEntryDTO;
import pl.pwr.scent_tracker.model.dto.mapper.CollectionEntryMapper;
import pl.pwr.scent_tracker.model.entity.CollectionEntry;
import pl.pwr.scent_tracker.model.entity.CollectionType;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.User;
import pl.pwr.scent_tracker.repository.CollectionEntryRepository;
import pl.pwr.scent_tracker.repository.PerfumeRepository;
import pl.pwr.scent_tracker.repository.UserRepository;
import pl.pwr.scent_tracker.service.CollectionService;

import java.util.Optional;

@Component
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionEntryRepository collectionEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public CollectionEntryDTO getCollectionEntryByUserAndPerfume(Long userId, Long perfumeId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new Exception("User not found");
        Optional<Perfume> perfume = perfumeRepository.findById(perfumeId);
        if (perfume.isEmpty()) throw new Exception("Perfume not found");
        Optional<CollectionEntry> collectionEntry = collectionEntryRepository.findByUserAndPerfume(user.get(), perfume.get());
        if (collectionEntry.isEmpty()) throw new Exception("Collection entry not found");
        return CollectionEntryMapper.toCollectionEntryDTO(collectionEntry.get());
    }

    @Override
    public CollectionEntryDTO getCollectionEntry(Long id) throws Exception {
        Optional<CollectionEntry> collectionEntry = collectionEntryRepository.findById(id);
        if (collectionEntry.isEmpty()) throw new Exception("Collection entry not found");
        return CollectionEntryMapper.toCollectionEntryDTO(collectionEntry.get());
    }

    @Override
    public CollectionEntryDTO saveCollectionEntry(CollectionEntryDTO entry) throws Exception {
        Optional<User> user = userRepository.findById(entry.getUserId());
        if (user.isEmpty()) throw new Exception("User not found");
        Optional<Perfume> perfume = perfumeRepository.findById(entry.getPerfumeId());
        if (perfume.isEmpty()) throw new Exception("Perfume not found");

        CollectionEntry collectionEntry = CollectionEntry.builder()
                .collectionType(entry.getCollectionType())
                .quantity(entry.getQuantity())
                .note(entry.getNote())
                .user(user.get())
                .perfume(perfume.get())
                .build();
        CollectionEntry newEntry = collectionEntryRepository.save(collectionEntry);
        return CollectionEntryMapper.toCollectionEntryDTO(newEntry);
    }

    @Override
    public CollectionEntryDTO updateCollectionEntry(CollectionEntryDTO oldEntry, CollectionEntryDTO newEntry) throws Exception {
        Optional<CollectionEntry> optional = collectionEntryRepository.findById(oldEntry.getId());
        if (optional.isEmpty()) throw new Exception("Collection not found");
        CollectionEntry old = optional.get();
        old.setCollectionType(newEntry.getCollectionType());
        old.setQuantity(newEntry.getQuantity());
        old.setNote(newEntry.getNote());
        CollectionEntry updated = collectionEntryRepository.save(old);
        return CollectionEntryMapper.toCollectionEntryDTO(updated);
    }

    @Override
    public CollectionEntryDTO saveOrUpdateEntry(Long userId, Long perfumeId, String type, int quantity, String note) throws Exception {
        CollectionEntryDTO entry = null;
        try {
            entry = getCollectionEntryByUserAndPerfume(userId, perfumeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (entry != null) {
            CollectionEntryDTO newEntry = CollectionEntryDTO.builder()
                    .collectionType(CollectionType.valueOf(type))
                    .quantity((short) quantity)
                    .note(note)
                    .build();
            return updateCollectionEntry(entry, newEntry);
        }
        CollectionEntryDTO newEntry = CollectionEntryDTO.builder()
                .collectionType(CollectionType.valueOf(type))
                .quantity((short) quantity)
                .note(note)
                .build();
        return saveCollectionEntry(newEntry);
    }
}
