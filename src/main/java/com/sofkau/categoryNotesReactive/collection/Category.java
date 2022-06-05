package com.sofkau.categoryNotesReactive.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "category")
public class Category {

    @Id
    private String id;

    private String title;

    private List<Note> notes;
}