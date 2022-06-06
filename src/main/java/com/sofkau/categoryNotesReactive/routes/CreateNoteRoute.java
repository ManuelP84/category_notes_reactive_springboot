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
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CreateNoteRoute  {

    @Bean
    public RouterFunction<ServerResponse> createNote(CreateNoteUseCase createNoteUseCase){
        return route(
                POST("api/v1/note/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(NoteDto.class)
                        .flatMap(noteDto -> createNoteUseCase.apply(noteDto))
                        .flatMap(noteDto -> ServerResponse.status((HttpStatus.CREATED))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(noteDto))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }

}