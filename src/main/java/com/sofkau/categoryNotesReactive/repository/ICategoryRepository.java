package com.sofkau.categoryNotesReactive.repository;

import com.sofkau.categoryNotesReactive.collection.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICategoryRepository extends ReactiveMongoRepository<Category, String> {
}
