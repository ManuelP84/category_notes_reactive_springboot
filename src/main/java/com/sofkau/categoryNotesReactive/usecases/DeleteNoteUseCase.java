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
public class DeleteNoteUseCase implements Function<NoteDto, Mono> {

    private final ICategoryRepository categoryRepository;
    private final NoteMapper noteMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public Mono<CategoryDto> apply(NoteDto noteDto) {
        return categoryRepository
                .findByCategoryId(noteDto.getCategoryId())
                .switchIfEmpty(Mono.error(() -> new Throwable("No categories found in the DB!")))
                .flatMap(category -> Boolean.TRUE.equals(category.isNote(noteDto.getId())) ? categoryRepository.save(category.deleteNote(noteMapper.convertDtoToEntity().apply(noteDto))): Mono.empty())
                .switchIfEmpty(Mono.error(() -> new Throwable("Note is not present!")))
                .map(category -> categoryMapper.convertEntityToDto().apply(category));
    }
}
