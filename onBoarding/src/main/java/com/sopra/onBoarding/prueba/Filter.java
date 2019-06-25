package com.sopra.onBoarding.prueba;


import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public <T> List<T> filter(List<T> list, Predicate<T> condition) {
        return list.stream()
                .filter(condition::testCondition)
                .collect(Collectors.toList());
    }

}