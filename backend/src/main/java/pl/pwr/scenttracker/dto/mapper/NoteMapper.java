package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.NoteDTO;
import pl.pwr.scenttracker.model.Note;

public class NoteMapper {
    public static NoteDTO toNoteDTO(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .name(note.getName())
                .build();
    }
}
