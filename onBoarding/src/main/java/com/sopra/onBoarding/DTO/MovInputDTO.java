package com.sopra.onBoarding.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.List;
@JsonPropertyOrder({ "title", "year", "genre","cast" })
public class MovInputDTO {
    private String title;
    private String genre;
    private int year;
    private List<ActorDTO> cast;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<ActorDTO> getCast() {
        return cast;
    }

    public void setCast(List<ActorDTO> cast) {
        this.cast = cast;
    }

    public static class Builder {
        private String title;
        private String genre;
        private int year;
        private List<ActorDTO> cast;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setCast(List<ActorDTO> cast) {
            this.cast = cast;
            return this;
        }

        public MovInputDTO build() {
            MovInputDTO movInputDTO = new MovInputDTO();
            movInputDTO.setTitle(title);
            movInputDTO.setGenre(genre);
            movInputDTO.setYear(year);
            movInputDTO.setCast(cast);
            return movInputDTO;
        }
    }


}
