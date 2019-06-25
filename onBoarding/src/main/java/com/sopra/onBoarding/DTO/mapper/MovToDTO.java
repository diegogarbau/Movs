package com.sopra.onBoarding.DTO.mapper;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovShortDTO;
import com.sopra.onBoarding.entities.Mov;

public class MovToDTO {
    public static MovShortDTO mapperShort(Mov mov) {
        return new MovShortDTO.Builder()
                .setTitle(mov.getTitle())
                .setYear(mov.getYear())
                .build();
    }

    public static MovDetailDTO mapperLong(Mov mov) {
        return new MovDetailDTO.Builder()
                .setTitle(mov.getTitle())
                .setGenre(mov.getGenre())
                .setYear(mov.getYear())
                .setCast(ActorMappers.ActorToDTO(mov.getCast()))
                .build();
    }
}
