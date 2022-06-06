package com.sofkau.categoryNotesReactive.routes;

import com.sofkau.categoryNotesReactive.model.CategoryDto;
import com.sofkau.categoryNotesReactive.usecases.GetAllCategoriesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetAllCategoriesRoute {

    @Bean
    public RouterFunction<ServerResponse> getAllCategories(GetAllCategoriesUseCase getAllCategoriesUseCase){
        return route(
                GET("api/v1/all").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllCategoriesUseCase.get(), CategoryDto.class))
        );
    }
}
