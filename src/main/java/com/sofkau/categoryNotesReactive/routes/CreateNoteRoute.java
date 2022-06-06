package com.sofkau.categoryNotesReactive.routes;

import com.sofkau.categoryNotesReactive.model.NoteDto;
import com.sofkau.categoryNotesReactive.usecases.CreateNoteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CreateNoteRoute  {

    @Bean
    public RouterFunction<ServerResponse> createNote(CreateNoteUseCase createNoteUseCase){
        return route(
                POST("api/v1/note/create"),
                request -> request.bodyToMono(NoteDto.class)
                        .flatMap(noteDto -> createNoteUseCase.apply(noteDto))
                        .flatMap(noteDto -> ServerResponse.status((HttpStatus.CREATED))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(noteDto))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }

}

    /*@Bean
    public RouterFunction<ServerResponse> createCategory(CreateCategoryUseCase createCategoryUseCase){
        return route(
                POST("api/v1/create"),
                request -> request.bodyToMono(CategoryDto.class)
                        .flatMap(categoryDto -> createCategoryUseCase.apply(categoryDto))
                        .flatMap(categoryDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(categoryDto))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );

    }*/