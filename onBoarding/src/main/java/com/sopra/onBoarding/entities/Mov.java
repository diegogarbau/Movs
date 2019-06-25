package com.sopra.onBoarding.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Mov {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long movId;
    @Column(name = "title")
    private String title;
    @Column(name = "genre")
    private String genre;
    @Column(name = "year")
    private int year;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "plays",
            joinColumns = @JoinColumn(name = "movies.id"),
            inverseJoinColumns = @JoinColumn(name = "actors.id"))
    private List<Actor> cast;

    public Long getMovId() {
        return movId;
    }

    public void setMovId(Long movId) {
        this.movId = movId;
    }

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

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "Mov{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }

    public static class Builder {
        private Long movId;
        private String title;
        private String genre;
        private int year;
        private List<Actor> cast;

        public Builder setMovId(Long movId) {
            this.movId = movId;
            return this;
        }

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

        public Builder setCast(List<Actor> cast) {
            this.cast = cast;
            return this;
        }

        public Mov build() {
            Mov mov = new Mov();
            mov.setMovId(movId);
            mov.setTitle(title);
            mov.setGenre(genre);
            mov.setYear(year);
            mov.setCast(cast);
            return mov;
        }
    }
}
