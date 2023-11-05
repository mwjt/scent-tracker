package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.ConcentrationDTO;
import pl.pwr.scent_tracker.model.entity.Concentration;

public class ConcentrationMapper {
    public static ConcentrationDTO toConcentrationDTO(Concentration concentration) {
        return ConcentrationDTO.builder()
                .id(concentration.getId())
                .name(concentration.getName())
                .build();
    }
}
