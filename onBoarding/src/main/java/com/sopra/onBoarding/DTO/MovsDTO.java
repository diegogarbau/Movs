package com.sopra.onBoarding.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.List;
@JsonRootName(value = "Movies")
public class MovsDTO {
    @JsonProperty(value = "movies")
    private List<MovShortDTO> movs;

    public MovsDTO() {
        super();
        movs = new ArrayList<>();
    }

    public List<MovShortDTO> getMovs() {
        return movs;
    }

    public void setMovs(List<MovShortDTO> movs) {
        this.movs = movs;
    }

    public MovsDTO insertAll(List<MovShortDTO> listaPeliculasDTO) {
        movs.addAll(listaPeliculasDTO);
        return this;
    }
}
