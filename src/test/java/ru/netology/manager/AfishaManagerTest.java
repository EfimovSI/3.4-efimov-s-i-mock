package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Movie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AfishaManagerTest {
    AfishaManager manager = new AfishaManager();

    Movie first = new Movie(1, "Url1", "First", "genre1");
    Movie second = new Movie(2, "Url2", "Second", "genre1");
    Movie third = new Movie(3, "Url3", "Third", "genre1");
    Movie fourth = new Movie(4, "Url4", "Fourth", "genre1");
    Movie fifth = new Movie(5, "Url5", "Fifth", "genre1");
    Movie sixth = new Movie(6, "Url6", "Sixth", "genre2");
    Movie seventh = new Movie(7, "Url7", "Seventh", "genre2");
    Movie eighth = new Movie(8, "Url8", "Eighth", "genre2");
    Movie ninth = new Movie(9, "Url9", "Ninth", "genre2");
    Movie tenth = new Movie(10, "Url10", "Tenth", "genre2");
    Movie eleventh = new Movie(11, "Url11", "Eleventh", "genre3");

    @Test
    void shouldUseDefaultConstructor() {
        assertEquals(10, manager.getMovieListLimit());
    }

    @Test
    void shouldUseCustomConstructor() {
        AfishaManager manager = new AfishaManager(5);

        assertEquals(5, manager.getMovieListLimit());
    }

    @Test
    void shouldAddFirstMovie() {
        manager.add(first);
        Movie[] expected;
        expected = new Movie[]{first};

        assertArrayEquals(expected, manager.getMovies());
    }

    @Test
    void shouldShowListOfOneWhenMoreFilmsAdded() {
        AfishaManager manager = new AfishaManager(1);
        manager.add(first);
        manager.add(second);
        manager.add(third);
        Movie[] expected = {third};

        assertArrayEquals(expected, manager.getList());
    }

    @Test
    void shouldShowDefaultListWhenMoreFilmsAdded() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eighth);
        manager.add(ninth);
        manager.add(tenth);
        manager.add(eleventh);
        Movie[] expected = {eleventh, tenth, ninth, eighth, seventh, sixth, fifth, fourth, third, second};

        assertArrayEquals(expected, manager.getList());
    }

    @Test
    void shouldShowListWhenLessFilmsAdded() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Movie[] expected = {fourth, third, second, first};

        assertArrayEquals(expected, manager.getList());
    }

    @Test
    void shouldShowEmptyList() {
        Movie[] expected = new Movie[0];

        assertArrayEquals(expected, manager.getList());
    }
}