package com.sofkau.categoryNotesReactive.routes;

import com.sofkau.categoryNotesReactive.model.NoteDto;
import com.sofkau.categoryNotesReactive.usecases.DeleteNoteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DeleteNoteRoute{

    @Bean
    public RouterFunction<ServerResponse> deleteNoteById(DeleteNoteUseCase deleteNoteUseCase){
        return route(
                DELETE("api/v1/note/delete").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(NoteDto.class)
                        .flatMap(deleteNoteUseCase::apply)
                        .flatMap(category -> ServerResponse.status(HttpStatus.ACCEPTED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(category))
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_FOUND).build()));
    }

}
