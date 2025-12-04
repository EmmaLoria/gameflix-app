package org.example.springinitializr.service;

import org.example.springinitializr.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    void getAllMovies_ShouldReturnList() {
        List<Movie> movies = movieService.getAllMovies();
        Assertions.assertNotNull(movies);
        Assertions.assertFalse(movies.isEmpty(), "Movie list should not be empty");
    }

    @Test
    void addMovie_ShouldIncreaseListSize() {
        int before = movieService.getAllMovies().size();

        Movie newMovie = new Movie(null, "GameFlix: The Movie", "Drama", 2025, 9.0);
        movieService.addMovie(newMovie);

        int after = movieService.getAllMovies().size();
        Assertions.assertEquals(before + 1, after);
    }

    @Test
    void getMovieById_ShouldReturnCorrectMovie() {
        // Grab an existing movie
        Movie first = movieService.getAllMovies().get(0);

        Optional<Movie> result = movieService.getMovieById(first.getId());

        Assertions.assertTrue(result.isPresent(), "Movie should be found by ID");
        Assertions.assertEquals(first.getTitle(), result.get().getTitle());
    }
}
