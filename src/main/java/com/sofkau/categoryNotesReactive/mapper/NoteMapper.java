package com.sofkau.categoryNotesReactive.mapper;

import com.sofkau.categoryNotesReactive.collection.Category;
import com.sofkau.categoryNotesReactive.collection.Note;
import com.sofkau.categoryNotesReactive.model.CategoryDto;
import com.sofkau.categoryNotesReactive.model.NoteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class NoteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Function<Note, NoteDto> convertEntityToDto(){
        return note -> modelMapper.map(note, NoteDto.class);
    }

    public Function<NoteDto, Note> convertDtoToEntity(){
        return noteDto -> modelMapper.map(noteDto, Note.class);
    }


}
