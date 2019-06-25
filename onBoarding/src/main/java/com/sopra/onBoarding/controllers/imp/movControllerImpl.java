package com.sopra.onBoarding.controllers.imp;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.DTO.MovsDTO;
import com.sopra.onBoarding.controllers.movController;
import com.sopra.onBoarding.services.MovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class movControllerImpl implements movController {
    private MovService movService;

    @Autowired
    public movControllerImpl(MovService movService) {
        this.movService = movService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/movies")
    public MovsDTO getAllMovies() {
        return movService.getAllMovies();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/movie/{id}/")
    public MovDetailDTO getMovie(@PathVariable("id") Long id) {
        return movService.getMovie(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/movie")
    public MovDetailDTO createMovie(@RequestBody MovInputDTO movInputDTO) {
        return movService.createMov(movInputDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/movie/{title}")
    public MovDetailDTO modifyMovie(@PathVariable("title") String title, @RequestBody MovInputDTO movInputDTO) {
        return movService.modifyMov(title, movInputDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable("id")  Long id) {
        this.movService.deleteMov(id);
    }
}
