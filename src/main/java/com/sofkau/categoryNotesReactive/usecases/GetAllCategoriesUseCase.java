package com.sofkau.categoryNotesReactive.usecases;

import com.sofkau.categoryNotesReactive.mapper.CategoryMapper;
import com.sofkau.categoryNotesReactive.model.CategoryDto;
import com.sofkau.categoryNotesReactive.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllCategoriesUseCase implements Supplier<Flux<CategoryDto>> {

    private final CategoryMapper categoryMapper;
    private final ICategoryRepository categoryRepository;

    @Override
    public Flux<CategoryDto> get() {
        return categoryRepository
                .findAll()
                .map(category -> categoryMapper.convertEntityToDto().apply(category))
                .switchIfEmpty(Flux.error(() -> new Throwable("No categories found in the DB!")));
    }
}
