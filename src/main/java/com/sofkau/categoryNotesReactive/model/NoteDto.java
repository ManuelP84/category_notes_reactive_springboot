package com.sofkau.categoryNotesReactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {

    private String id= UUID.randomUUID().toString().substring(0, 10);

    private String categoryId;

    private String title;

    private String message;

    private Boolean isDone;
}
