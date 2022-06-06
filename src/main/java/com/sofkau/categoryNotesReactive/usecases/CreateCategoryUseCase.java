package com.sofkau.categoryNotesReactive.usecases;

import com.sofkau.categoryNotesReactive.mapper.CategoryMapper;
import com.sofkau.categoryNotesReactive.model.CategoryDto;
import com.sofkau.categoryNotesReactive.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CreateCategoryUseCase implements Function<CategoryDto, Mono<CategoryDto>> {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public Mono<CategoryDto> apply(CategoryDto categoryDto) {
        return validateCategoryDto(categoryDto)
                .flatMap(catDto -> categoryRepository.save(categoryMapper.convertDtoToEntity().apply(catDto)))
                .map(category -> categoryMapper.convertEntityToDto().apply(category));
    }

    private Boolean validateCategoryProps(CategoryDto categoryDto){
        return categoryDto.getTitle() != null;
    }

    private Mono<CategoryDto> validateCategoryDto(CategoryDto categoryDto) {
        return validateCategoryProps(categoryDto)?
                Mono.just(categoryDto)
                :
                Mono.error(() -> new Throwable("The category does not have all the necessary attributes."));
    }
}
