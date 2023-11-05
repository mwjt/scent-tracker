package pl.pwr.scent_tracker.model.dto.mapper;

import pl.pwr.scent_tracker.model.dto.entity.NoteDTO;
import pl.pwr.scent_tracker.model.entity.Note;

public class NoteMapper {
    public static NoteDTO toNoteDTO(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .name(note.getName())
                .build();
    }
}
