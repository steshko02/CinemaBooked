package com.example.cinema.dto.model;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto  implements Serializable {

    private Long id;

    private String name;

    private Integer duration;

    private Set<Long> genres = new HashSet<>();

    private String description;

    private Float rating;

    private String imgUrl;

    private String trailerUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(id, movieDto.id) && Objects.equals(name, movieDto.name) && Objects.equals(duration, movieDto.duration) && Objects.equals(genres, movieDto.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration, genres);
    }
}
