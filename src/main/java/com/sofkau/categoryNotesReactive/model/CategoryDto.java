package com.sofkau.categoryNotesReactive.model;

import com.sofkau.categoryNotesReactive.collection.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String id;

    private String title;

    private List<Note> notes;
}
