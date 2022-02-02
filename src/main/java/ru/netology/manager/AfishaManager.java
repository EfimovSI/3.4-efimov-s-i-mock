package ru.netology.manager;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Movie;

@NoArgsConstructor
@Data
public class AfishaManager {
    private int movieListLimit = 10;

    private Movie[] movies;

    public AfishaManager(int movieListLimit) {
        this.movieListLimit = movieListLimit;
    }

    public void add(Movie movie) {
        int length;
        if (movies == null) {
            Movie[] tmp = new Movie[1];
            tmp[0] = movie;
            movies = tmp;
        } else {
            length = movies.length + 1;
            Movie[] tmp = new Movie[length];
            System.arraycopy(movies, 0, tmp, 0, movies.length);
            int lastIndex = tmp.length - 1;
            tmp[lastIndex] = movie;
            movies = tmp;
        }
    }

    public Movie[] getList() {
        int resultLength;
        if (movies == null) {
            Movie[] tmp = new Movie[0];
            movies = tmp;
            return movies;
        } else {
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
}
