package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Movie;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieRepository {
    private Movie[] movies = new Movie[0];

    public Movie[] findAll() {
        return this.movies;
    }

    public void save(Movie movie) {
        int length = movies.length + 1;
        Movie[] tmp = new Movie[length];
        System.arraycopy(movies, 0, tmp, 0, movies.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = movie;
        this.movies = tmp;
    }

    public Movie findById(int id) {

        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            return;
        }
        int length = movies.length - 1;
        Movie[] tmp = new Movie[length];
        int index = 0;
        for (Movie movie : movies) {
            if (movie.getId() != id) {
                tmp[index] = movie;
                index++;
            }
        }
        this.movies = tmp;
    }

    public void removeAll() {
        this.movies = new Movie[0];
    }
}
