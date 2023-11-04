package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.ConcentrationDTO;
import pl.pwr.scenttracker.model.Concentration;

public class ConcentrationMapper {
    public static ConcentrationDTO toConcentrationDTO(Concentration concentration) {
        return ConcentrationDTO.builder()
                .id(concentration.getId())
                .name(concentration.getName())
                .build();
    }
}
