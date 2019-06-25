package com.sopra.onBoarding.controllers;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.DTO.MovsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "movies")
public interface movController {

    @ApiOperation(value = "movies", notes = "Endpoint to get all the movies", response = MovsDTO.class)
    MovsDTO getAllMovies();

    @ApiOperation(value = "movies", notes = "Endpoint to get movie by id", response = MovDetailDTO.class)
    MovDetailDTO getMovie(Long id);

    @ApiOperation(value = "movies", notes = "Endpoint to create a previous movie", response = MovDetailDTO.class)
    MovDetailDTO createMovie(MovInputDTO movInputDTO);

    @ApiOperation(value = "movies", notes = "Endpoint to modify a previous movie", response = MovDetailDTO.class)
    MovDetailDTO modifyMovie(String title, MovInputDTO movInputDTO);

    @ApiOperation(value = "movies", notes = "Endpoint to delete a previous movie by id")
    void deleteMovie(Long id);

}
