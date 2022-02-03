package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Movie;
import ru.netology.repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class AfishaManagerTest {
    private MovieRepository repository = Mockito.mock(MovieRepository.class);
    private AfishaManager manager = new AfishaManager(repository);
//    AfishaManager manager = new AfishaManager();

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
//        manager.add(first);
        Movie[] returned = {first};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{first};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldShowListOfOneWhenMoreFilmsAdded() {
        AfishaManager manager = new AfishaManager(1, repository);
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
        Movie[] returned = {first, second, third};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = {third};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldShowDefaultListWhenMoreFilmsAdded() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.add(fourth);
//        manager.add(fifth);
//        manager.add(sixth);
//        manager.add(seventh);
//        manager.add(eighth);
//        manager.add(ninth);
//        manager.add(tenth);
//        manager.add(eleventh);
        Movie[] returned = {first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = {eleventh, tenth, ninth, eighth, seventh, sixth, fifth, fourth, third, second};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldShowListWhenLessFilmsAdded() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.add(fourth);
        Movie[] returned = {first, second, third, fourth};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = {fourth, third, second, first};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldShowEmptyList() {
        Movie[] returned = new Movie[0];
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[0];

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldFindByIdWhenExist() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.add(fourth);
//        manager.add(fifth);
        Movie returned = fourth;
        doReturn(returned).when(repository).findById(4);

        assertEquals(fourth, manager.findById(4));

        verify(repository).findById(4);
    }

    @Test
    void shouldFindByIdWhenNotExist() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.add(fourth);
//        manager.add(fifth);
        doReturn(null).when(repository).findById(10);

        assertNull(manager.findById(10));

        verify(repository).findById(10);
    }

    @Test
    void shouldRemoveByIdWhenExist() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.add(fourth);
//        manager.add(fifth);
//        manager.removeById(4);
        Movie[] returned = {first, second, third, fifth};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{fifth, third, second, first};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldRemoveByIdWhenNotExist() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.removeById(10);
        Movie[] returned = {first, second, third};
        doReturn(returned).when(repository).findAll();

        Movie[] expected = new Movie[]{third, second, first};

        assertArrayEquals(expected, manager.getList());

        verify(repository).findAll();
    }

    @Test
    void shouldRemoveAll() {
//        manager.add(first);
//        manager.add(second);
//        manager.add(third);
//        manager.removeAll();
        Movie[] returned = new Movie[0];
        doReturn(returned).when(repository).findAll();

        assertArrayEquals(new Movie[0], manager.getList());

        verify(repository).findAll();
    }
}