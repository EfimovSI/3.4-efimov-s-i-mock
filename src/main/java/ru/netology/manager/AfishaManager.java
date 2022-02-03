package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Movie;
import ru.netology.repository.MovieRepository;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AfishaManager {
    private int movieListLimit = 10;

    private MovieRepository repository = new MovieRepository();

    public AfishaManager(int movieListLimit) {
        this.movieListLimit = movieListLimit;
    }

    public void add(Movie movie) {
        repository.save(movie);
    }

    public Movie findById(int id) {
        return repository.findById(id);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void removeAll() {
        repository.removeAll();
    }

    public Movie[] getList() {
        int resultLength;
        Movie[] movies = repository.findAll();
        if (movies.length >= movieListLimit) {
            resultLength = movieListLimit;
        } else {
            resultLength = movies.length;
        }
        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < resultLength; i++) {
            int index = movies.length - 1 - i;
            result[i] = movies[index];
        }
        return result;
    }
}
