package com.sopra.onBoarding.utils;

import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.entities.Mov;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MovTestUtils extends TestUtils {
    private static final int INITIAL_YEAR = 1888;


    private MovTestUtils() {
    }

    private static Integer randomYearGenerator() {
        int dif = Calendar.getInstance().get(Calendar.YEAR) - INITIAL_YEAR;
        return INITIAL_YEAR + random.nextInt(dif);
    }


    public static Mov movRandomGenerator() {
        return new Mov.Builder()
                .setMovId(random.nextLong())
                .setTitle(randomStringGenerator())
                .setGenre(randomStringGenerator())
                .setYear(randomYearGenerator())
                .setCast(ActorTestUtils.randomCastGenerator())
                .build();
    }

    public static MovInputDTO movInputDTORandomGenerator() {
        return new MovInputDTO.Builder()
                .setTitle(randomStringGenerator())
                .setGenre(randomStringGenerator())
                .setYear(randomYearGenerator())
                .setCast(ActorTestUtils.randomCastDTOGenerator())
                .build();
    }

    public static List<Mov> movListRandomGenerator() {
        return IntStream.range(0, random.nextInt(10))
                .mapToObj(i -> movRandomGenerator())
                .collect(Collectors.toList());
    }

    public static List<Mov> movListRandomGenerator(int j) {
        return IntStream.range(0, j)
                .mapToObj(i -> movRandomGenerator())
                .collect(Collectors.toList());
    }

}