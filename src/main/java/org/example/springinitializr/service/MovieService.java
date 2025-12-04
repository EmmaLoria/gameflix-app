package org.example.springinitializr.service;

import org.example.springinitializr.model.Movie;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final List<Movie> movies = new ArrayList<>();
    private long nextId = 1L;

    @PostConstruct
    public void init() {
        // Seed a few movies so tests have data to work with
        addMovie(new Movie(null, "Inception", "Sci-Fi", 2010, 8.8));
        addMovie(new Movie(null, "The Lion King", "Animation", 1994, 8.5));
        addMovie(new Movie(null, "Interstellar", "Sci-Fi", 2014, 8.6));
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies); // return a copy so tests don’t accidentally mutate internal list
    }

    public Movie addMovie(Movie movie) {
        movie.setId(nextId++);
        movies.add(movie);
        return movie;
    }

    public Optional<Movie> getMovieById(Long id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .toList();
    }
}
