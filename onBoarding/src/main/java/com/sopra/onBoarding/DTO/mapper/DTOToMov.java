package com.sopra.onBoarding.DTO.mapper;

import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.core.utils.MovChecker;
import com.sopra.onBoarding.entities.Mov;

public class DTOToMov {
    public static Mov mapperLong(MovInputDTO movInputDTO) {
        return new Mov.Builder()
                .setTitle(movInputDTO.getTitle())
                .setGenre(movInputDTO.getGenre())
                .setYear(movInputDTO.getYear())
                .setCast(ActorMappers.DTOToActor(movInputDTO.getCast()))
                .build();
    }

    public static Mov SetMovParams(MovInputDTO movInputDTO, Mov movDestiny) {
        if (notNullOrEmpty(movInputDTO.getTitle()))
            movDestiny.setTitle(movInputDTO.getTitle());
        if (notZero(movInputDTO.getYear()))
            movDestiny.setYear(movInputDTO.getYear());
        if (notNullOrEmpty(movInputDTO.getGenre()))
            movDestiny.setGenre(movInputDTO.getGenre());
        if (notNullOrEmpty(movInputDTO.getTitle()))
            movDestiny.setCast(ActorMappers.DTOToActor(movInputDTO.getCast()));
        return movDestiny;
    }

    private static boolean notZero(int i) {
        return  (i != 0);
    }
    private static boolean notNullOrEmpty(String t) {
        return (t != null && t != "");
    }

}