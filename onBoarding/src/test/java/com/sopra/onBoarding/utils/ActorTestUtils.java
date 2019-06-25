package com.sopra.onBoarding.utils;

import com.sopra.onBoarding.DTO.ActorDTO;
import com.sopra.onBoarding.entities.Actor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

 class ActorTestUtils extends TestUtils {
    private ActorTestUtils() {
    }

    private static Actor randomActorGenerator() {
        return new Actor.Builder()
                .setName(randomStringGenerator())
                .build();
    }
    private static ActorDTO randomActorDTOGenerator() {
        return new ActorDTO.Builder()
                .setName(randomStringGenerator())
                .build();
    }

    static List<Actor> randomCastGenerator() {
        return IntStream.rangeClosed(1, random.nextInt(8)+2)
                .mapToObj(i -> randomActorGenerator())
                .collect(Collectors.toList());
    }

    static List<ActorDTO> randomCastDTOGenerator() {
        return IntStream.rangeClosed(1, random.nextInt(8)+2)
                .mapToObj(i -> randomActorDTOGenerator())
                .collect(Collectors.toList());
    }

}
