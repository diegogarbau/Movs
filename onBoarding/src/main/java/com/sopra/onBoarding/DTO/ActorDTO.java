package com.sopra.onBoarding.DTO;

public class ActorDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder {
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;

        }

        public ActorDTO build() {
            ActorDTO actor = new ActorDTO();
            actor.setName(name);
            return actor;
        }
    }
}
