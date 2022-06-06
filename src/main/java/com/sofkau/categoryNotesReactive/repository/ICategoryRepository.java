package com.sofkau.categoryNotesReactive.repository;

import com.sofkau.categoryNotesReactive.collection.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ICategoryRepository extends ReactiveMongoRepository<Category, String> {

    public Mono<Category> findByCategoryId(String categoryId);
}
