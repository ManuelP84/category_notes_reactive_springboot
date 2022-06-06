package com.sofkau.categoryNotesReactive.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    private String id;

    private String title;

    private String message;

    private Boolean isDone;
}
