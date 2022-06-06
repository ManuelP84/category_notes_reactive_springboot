package com.sofkau.categoryNotesReactive.usecases;

import com.sofkau.categoryNotesReactive.mapper.CategoryMapper;
import com.sofkau.categoryNotesReactive.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DeleteCategoryUseCase implements Function<String, Mono> {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public Mono<Void> apply(String categoryId){
        return categoryRepository
                .findById(categoryId)
                .switchIfEmpty(Mono.error(() -> new Throwable("No categories found in the DB!")))
                .flatMap(category -> categoryRepository.deleteById(category.getId()));
    }
}
