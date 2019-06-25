package com.sopra.onBoarding.core.utils;

import com.sopra.onBoarding.DTO.ActorDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

import static java.lang.String.format;

@Component
public class MovChecker {
    private static final int initialYear = 1888;

    public static String checkParamsInput(MovInputDTO movInputDTO) {
        if (movInputDTO == null) return "Pelicula Null";
        if (movInputDTO.getTitle() == null || movInputDTO.getTitle() == "") return "Error en el titulo";
        if (movInputDTO.getGenre() == null || movInputDTO.getGenre() == "") return "Error en el genero";
        if (checkYear(movInputDTO)) return "Error en el año";
        if (!checkCast(movInputDTO.getCast())) return format("Error en los actores: %d", movInputDTO.getCast().size());

        return "Valida";
    }

    private static boolean checkYear(MovInputDTO movInputDTO) {
        return movInputDTO.getYear() < initialYear || movInputDTO.getYear() > Calendar.getInstance().get(Calendar.YEAR);
    }

    private static boolean checkCast(List<ActorDTO> cast) {
        if (cast == null || cast.isEmpty()) return false;
        return cast.stream()
                .noneMatch(MovChecker::checkActor);

    }

    private static boolean checkActor(ActorDTO actorDTO) {
        return (actorDTO == null || actorDTO.getName().length() == 0);
    }




}
