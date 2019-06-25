package com.sopra.onBoarding.controllers.imp;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class movControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Transactional
    public void getAllMovies() throws Exception {
        ResultActions result = mockMvc.perform(get("/movies/"));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.movies.[0].title").value("Titanic"))
                .andExpect(jsonPath("$.movies.[0].year").value(1997))
                .andExpect(jsonPath("$.movies.[1].title").value("BraveHeart"))
                .andExpect(jsonPath("$.movies.[1].year").value(1996))
                .andExpect(jsonPath("$.movies.[2].title").value("Avatar"))
                .andExpect(jsonPath("$.movies.[2].year").value(2009))
                .andExpect(jsonPath("$.movies.[3].title").value("Cadena Perpetua"))
                .andExpect(jsonPath("$.movies.[3].year").value(1994));
    }

    @Test
    @Transactional
    public void getMovieWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/movie/{id}/", 3));
        result.andDo(print());
        result.andExpect(status().isOk());
    }

    @Test
//    @Transactional
    public void getMovieNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(get("/movie/{id}/", -1));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void createMovieWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(post("/movie/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Robbin Williams\" } ], \"genre\": \"Animation\", \"title\": \"Aladdin\", \"year\": 1992}"
                ));
        result.andDo(print());
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Aladdin"))
                .andExpect(jsonPath("$.year").value(1992))
                .andExpect(jsonPath("$.genre").value("Animation"));
    }

    @Test
    @Transactional
    public void createMovieSameTitleWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(post("/movie/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Christian Bale\" } ], \"genre\": \"Action\", \"title\": \"Batman\", \"year\": 2004}"
                ));
        result.andDo(print());
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Batman"))
                .andExpect(jsonPath("$.year").value(2004))
                .andExpect(jsonPath("$.genre").value("Action"));
    }

    @Test
    @Transactional
    public void createMovieDuplicatedTest() throws Exception {
        ResultActions result = mockMvc.perform(post("/movie/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Mel Gibson\" } ], \"genre\": \"Drama\", \"title\": \"BraveHeart\", \"year\": 1996}"
                ));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void createMovieWrongInputTest() throws Exception {
        ResultActions result = mockMvc.perform(post("/movie/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Sam Worthington\" } ], \"genre\": \"Sci-fi\", \"title\": \"Avatar\", \"year\": 0}"
                ));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void modifyMovieWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(put("/movie/{title}", "Batman")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Michael Keaton\" }], \"genre\": \"Accion\", \"title\": \"Batman\", \"year\": 1989}"
                ));
        result.andDo(print());
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Batman"))
                .andExpect(jsonPath("$.year").value(1989))
                .andExpect(jsonPath("$.genre").value("Accion"))
                .andExpect(jsonPath("$.cast.[0].name").value("Michael Keaton"));
    }

    @Test
    @Transactional
    public void modifyMovieNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(put("/movie/{title}", "Ira de Titanes")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Sam Worthington\" } ], \"genre\": \"Sci-fi\", \"title\": \"Ira de Titanes\", \"year\": 2009}"
                ));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void modifyMovieWrongInputTest() throws Exception {
        ResultActions result = mockMvc.perform(put("/movie/{title}", "Ira de Titanes")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(
                        "{ \"cast\": [ { \"name\": \"Sam Worthington\" } ], \"genre\": \"\", \"title\": \"Avatar\", \"year\": 2009}"
                ));
        result.andDo(print());
        result.andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void deleteMovieWorksTest() throws Exception {
        ResultActions result = mockMvc.perform(delete("/movie/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        result.andDo(print());
        result.andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void deleteMovieNotFoundTest() throws Exception {
        ResultActions result = mockMvc.perform(delete("/movie/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        result.andDo(print());
        result.andExpect(status().isNotFound());
    }
}