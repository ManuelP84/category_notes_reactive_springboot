package com.sofkau.categoryNotesReactive.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "category")
public class Category {

    @Id
    private String id;

    private String categoryId;

    private String title;

    private List<Note> notes;

    /*public Category insertNote(Note note){
        this.notes.add(note);
        return this;
    }*/

    public Optional<Category> insertNote(Note updatedNote){
        return Stream.of(this)
                .map(category -> {
                    Category updatedCategory = new Category();
                    updatedCategory.setId(category.getId());
                    updatedCategory.setCategoryId(category.getCategoryId());
                    updatedCategory.setTitle(category.getTitle());
                    updatedCategory.setNotes(category.getNotes());
                    updatedCategory.getNotes().add(updatedNote);
                    return updatedCategory;
                })
                .reduce((resume, category) -> category);
    }

    public Boolean isNote(String noteId){
        return this
                .getNotes()
                .stream()
                .anyMatch(note -> note.getId().equals(noteId));
    }

    public Category deleteNote(Note noteDeleted) {
        this.getNotes().remove(noteDeleted);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, title, notes);
    }
}
