package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.TagDTO;
import pl.pwr.scenttracker.model.Tag;

public class TagMapper {
    public static TagDTO toTagDTO(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
