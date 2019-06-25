package com.sopra.onBoarding.DTO.mapper;

import com.sopra.onBoarding.DTO.ActorDTO;
import com.sopra.onBoarding.entities.Actor;

import java.util.List;
import java.util.stream.Collectors;

public class ActorMappers {
    public static List<ActorDTO> ActorToDTO(List<Actor> listActor) {
        return listActor.stream()
                .map(a -> new ActorDTO.Builder()
                        .setName(a.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<Actor> DTOToActor(List<ActorDTO> listDto) {
        return listDto.stream()
                .map(actor -> new Actor.Builder()
                        .setName(actor.getName())
                        .build())
                .collect(Collectors.toList());
    }
}

