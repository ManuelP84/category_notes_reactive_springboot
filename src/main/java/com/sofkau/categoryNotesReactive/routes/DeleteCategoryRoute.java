package com.sofkau.categoryNotesReactive.routes;

import com.sofkau.categoryNotesReactive.usecases.DeleteCategoryUseCase;
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
public class DeleteCategoryRoute {

    @Bean
    public RouterFunction<ServerResponse> deleteCategoryById(DeleteCategoryUseCase deleteCategoryUseCase){
        return route(
                DELETE("api/v1/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteCategoryUseCase
                        .apply(request.pathVariable("id"))
                        .flatMap((mono) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}

