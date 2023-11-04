package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.AccordDTO;
import pl.pwr.scenttracker.model.Accord;

public class AccordMapper {
    public static AccordDTO toAccordDTO(Accord accord) {
        return AccordDTO.builder()
                .id(accord.getId())
                .name(accord.getName())
                .build();
    }
}
