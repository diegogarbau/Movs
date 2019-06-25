package com.sopra.onBoarding.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "Movie")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "year"})
public class MovShortDTO {
    private String title;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public static class Builder {
        private String title;
        private int year;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public MovShortDTO build() {
            MovShortDTO movShortDTO = new MovShortDTO();
            movShortDTO.setTitle(title);
            movShortDTO.setYear(year);
            return movShortDTO;
        }
    }
}
