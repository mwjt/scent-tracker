package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.AccordDTO;
import pl.pwr.scent_tracker.model.entity.Accord;

public class AccordMapper {
    public static AccordDTO toAccordDTO(Accord accord) {
        return AccordDTO.builder()
                .id(accord.getId())
                .name(accord.getName())
                .build();
    }
}
