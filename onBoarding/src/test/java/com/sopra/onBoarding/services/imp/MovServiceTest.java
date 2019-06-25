package com.sopra.onBoarding.services.imp;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.DTO.MovsDTO;
import com.sopra.onBoarding.DTO.mapper.DTOToMov;
import com.sopra.onBoarding.entities.Mov;
import com.sopra.onBoarding.exceptions.notFoundExceptions.NotFoundException;
import com.sopra.onBoarding.exceptions.wrongInputExceptions.DuplicatedException;
import com.sopra.onBoarding.exceptions.wrongInputExceptions.WrongInputLongException;
import com.sopra.onBoarding.persistance.MovDAO;
import com.sopra.onBoarding.services.MovService;
import com.sopra.onBoarding.utils.MovTestUtils;
import com.sopra.onBoarding.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.IsNot.not;

@RunWith(MockitoJUnitRunner.class)
public class MovServiceTest {

    @Mock
    private MovDAO movDAOMock;

    private MovService movServiceTest;

    @Before
    public void setUp() {
        movServiceTest = new MovServiceImpl(movDAOMock);
    }

    @Test
    public void getAllMoviesEmptyTest() {

        when(movDAOMock.findAll()).thenReturn(new ArrayList<>());
        MovsDTO resultDTO = movServiceTest.getAllMovies();
        assertTrue(resultDTO.getMovs().isEmpty());
    }

    @Test
    public void getAllMoviesFullfilledTest() {

        List<Mov> movList = MovTestUtils.movListRandomGenerator();
        when(movDAOMock.findAll()).thenReturn(movList);
        MovsDTO resultDTO = movServiceTest.getAllMovies();
        assertEquals(resultDTO.getMovs().size(), movList.size());
    }

    @Test
    public void getMovieOkTest() {
        Mov mov = MovTestUtils.movRandomGenerator();
        Optional<Mov> movOpt = Optional.of(mov);
        when(movDAOMock.findById(any())).thenReturn(movOpt);
        MovDetailDTO resultDTO = movServiceTest.getMovie(TestUtils.random.nextLong());
        assertEquals(resultDTO.getTitle(), mov.getTitle());
        assertEquals(resultDTO.getGenre(), mov.getGenre());
        assertEquals(resultDTO.getYear(), mov.getYear());
    }

    @Test(expected = NotFoundException.class)
    public void getMovieNotFoundTest() {
        movServiceTest.getMovie(0L);
    }

    @Test
    public void createMovWorksTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        Mov movSaved = DTOToMov.mapperLong(movInputDTO);

        when(movDAOMock.save(any())).thenReturn(movSaved);

        MovDetailDTO resultDTO = movServiceTest.createMov(movInputDTO);

        assertEquals(resultDTO.getTitle(), movInputDTO.getTitle());
        assertEquals(resultDTO.getGenre(), movInputDTO.getGenre());
        assertEquals(resultDTO.getYear(), movInputDTO.getYear());
    }

    @Test(expected = DuplicatedException.class)
    public void createMovDuplicatedMovieTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        Optional<Mov> movPrev = Optional.of(new Mov());
        when(movDAOMock.findByTitleAndYear(movInputDTO.getTitle(), movInputDTO.getYear())).thenReturn(movPrev);

        movServiceTest.createMov(movInputDTO);
    }

    @Test(expected = WrongInputLongException.class)
    public void createMovInvalidParamTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        movInputDTO.setTitle("");
        movServiceTest.createMov(movInputDTO);
    }

    @Test
    public void modifyMovWorksTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        Mov mov = DTOToMov.mapperLong(movInputDTO);
        mov.setMovId(TestUtils.random.nextLong());
        Optional<Mov> movOptional = Optional.of(mov);
        mov.setTitle("aaa");
        mov.setYear(2000);

        when(movDAOMock.findByTitle(any())).thenReturn(movOptional);
        when(movDAOMock.save(any())).thenReturn(mov);
        MovDetailDTO resultDTO = movServiceTest.modifyMov(mov.getTitle(), movInputDTO);

        assertEquals(resultDTO.getTitle(), movInputDTO.getTitle());
        assertEquals(resultDTO.getYear(), movInputDTO.getYear());
    }

    @Test(expected = NotFoundException.class)
    public void modifyMovNotFoundMovieTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        movServiceTest.modifyMov(TestUtils.randomStringGenerator(), movInputDTO);
    }

    @Test(expected = WrongInputLongException.class)
    public void modifyMovInvalidParamTest() {
        MovInputDTO movInputDTO = MovTestUtils.movInputDTORandomGenerator();
        movInputDTO.setGenre("");
        movServiceTest.modifyMov(TestUtils.randomStringGenerator(), movInputDTO);
    }

    @Test
    public void deleteMovByIdWorksTest() {
        Mov mov = MovTestUtils.movRandomGenerator();
        mov.setMovId(TestUtils.random.nextLong());
        Optional<Mov> movOptional = Optional.of(mov);

        when(movDAOMock.findById(any())).thenReturn(movOptional);
        movServiceTest.deleteMov(mov.getMovId());
        assertTrue(true);
    }

    @Test(expected = NotFoundException.class)
    public void deleteMovByIdNotFoundMovieTest() {
        Optional<Mov> movOptional = Optional.empty();

        when(movDAOMock.findById(any())).thenReturn(movOptional);
        movServiceTest.deleteMov(TestUtils.random.nextLong());
    }

    @Test
    public void createMovsListTest() {
        int k = TestUtils.random.nextInt(20);
        List<Mov> z = MovTestUtils.movListRandomGenerator(k);
                assertEquals(k,z.size());

    }

}