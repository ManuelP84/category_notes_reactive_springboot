package com.sofkau.categoryNotesReactive.usecases;

import com.sofkau.categoryNotesReactive.mapper.CategoryMapper;
import com.sofkau.categoryNotesReactive.mapper.NoteMapper;
import com.sofkau.categoryNotesReactive.model.CategoryDto;
import com.sofkau.categoryNotesReactive.model.NoteDto;
import com.sofkau.categoryNotesReactive.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class CreateNoteUseCase implements Function<NoteDto, Mono<CategoryDto>> {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper      categoryMapper;
    private final NoteMapper          noteMapper;

    @Override
    public Mono<CategoryDto> apply(NoteDto noteDto) {
        return categoryRepository
                .findByCategoryId(noteDto.getCategoryId())
                .flatMap((category) -> categoryRepository
                        .save(category.insertNote(noteMapper.convertDtoToEntity().apply(noteDto))))
                .map(category -> categoryMapper.convertEntityToDto().apply(category))
                .switchIfEmpty(Mono.error(() -> new Throwable("Category not found!")));
    }
}
