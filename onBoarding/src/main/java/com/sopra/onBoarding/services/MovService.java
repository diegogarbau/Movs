package com.sopra.onBoarding.services;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.DTO.MovsDTO;

public interface MovService {
    MovsDTO getAllMovies();
    MovDetailDTO getMovie(Long id);
    MovsDTO getMoviesByTitle(String title);
    MovsDTO getMovieByTitleAndYear(String title, int year);
    MovDetailDTO createMov(MovInputDTO movInputDTO);
    MovDetailDTO modifyMov(String title, MovInputDTO movInputDTO);
    void deleteMov(Long id);
}
