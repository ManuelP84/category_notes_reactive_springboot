package com.sofkau.categoryNotesReactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private String id;

    private String title;

    private String message;

    private Boolean isDone;
}
