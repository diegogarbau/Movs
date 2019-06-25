package com.sopra.onBoarding.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long actorId;
    @Column(name = "name")
    private String name;

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private Long actorId;
        private String name;
        private List<Mov> filmography;

        public Builder setActorId(Long actorId) {
            this.actorId = actorId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Actor build() {
            Actor actor = new Actor();
            actor.setActorId(actorId);
            actor.setName(name);
            return actor;
        }
    }
}
