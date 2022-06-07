package com.tisv2000.integration.controller;

import com.tisv2000.dto.movie.MovieCreateEditDto.Fields;
import com.tisv2000.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.tisv2000.dto.movie.MovieCreateEditDto.Fields.title;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testcontainers.shaded.org.hamcrest.collection.IsCollectionWithSize.hasSize;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class MovieControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findALl() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("movie/movies"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attribute("movies", hasSize(4)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/movie/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("movie/movie"))
                .andExpect(model().attributeExists("movie"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/movies")
                .param(title, "Avatar")
                .param(Fields.year, "2009")
                .param(Fields.country, "the USA")
                .param(Fields.image, "some image")
                .param(Fields.genre, "SCIENCE_FICTION")
                .param(Fields.description, "Some description")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/movies/{\\d+}")
        );
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(post("/movies/1/update")
                .param(title, "Test123")
                .param(Fields.year, "2009")
                .param(Fields.country, "the USA")
                .param(Fields.image, "some image")
                .param(Fields.genre, "SCIENCE_FICTION")
                .param(Fields.description, "Some description")
        ).andExpectAll(
                status().is3xxRedirection(),
                redirectedUrlPattern("/movies/1")
        );
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/movies/1/delete"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/movies")
                );
    }
}
