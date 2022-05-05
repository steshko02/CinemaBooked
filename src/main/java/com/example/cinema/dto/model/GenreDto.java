package com.example.cinema.dto.model;

import com.example.cinema.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto implements Serializable {
    private Long id;

    private String name;

    private Set<Long> movies = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genreDto = (GenreDto) o;
        return Objects.equals(id, genreDto.id) && Objects.equals(name, genreDto.name) && Objects.equals(movies, genreDto.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, movies);
    }
}
