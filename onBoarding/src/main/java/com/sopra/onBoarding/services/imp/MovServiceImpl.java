package com.sopra.onBoarding.services.imp;

import com.sopra.onBoarding.DTO.MovDetailDTO;
import com.sopra.onBoarding.DTO.MovInputDTO;
import com.sopra.onBoarding.DTO.MovsDTO;
import com.sopra.onBoarding.DTO.mapper.DTOToMov;
import com.sopra.onBoarding.DTO.mapper.MovToDTO;
import com.sopra.onBoarding.core.utils.MovChecker;
import com.sopra.onBoarding.entities.Mov;
import com.sopra.onBoarding.exceptions.notFoundExceptions.NotFoundException;
import com.sopra.onBoarding.exceptions.wrongInputExceptions.DuplicatedException;
import com.sopra.onBoarding.exceptions.wrongInputExceptions.WrongInputLongException;
import com.sopra.onBoarding.persistance.MovDAO;
import com.sopra.onBoarding.services.MovService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class MovServiceImpl implements MovService {
    private MovDAO movDao;

    @Autowired
    public MovServiceImpl(MovDAO movDao) {
        this.movDao = movDao;
    }

    @Override
    public MovsDTO getAllMovies() {
        return new MovsDTO().insertAll(movDao.findAll()
                .stream()
                .map(MovToDTO::mapperShort)
                .collect(Collectors.toList()));
    }

    @Override
    public MovDetailDTO getMovie(Long id) {
        return MovToDTO.mapperLong(movDao.findById(id).orElseThrow(() ->
                new NotFoundException(format("La pelicula no figura en la base de datos"))));
    }

    @Override
    public MovsDTO getMoviesByTitle(String title) {
        Predicate<Mov> filterByTitle = mov -> mov.getTitle().contains(title);
        return new MovsDTO().insertAll(movDao.findAll()
                .stream()
                .filter(filterByTitle)
                .map(MovToDTO::mapperShort)
                .collect(Collectors.toList()));
    }

    @Override
    public MovsDTO getMovieByTitleAndYear(String title, int year) {
        Predicate<Mov> filterByTitle = mov -> mov.getTitle().contains(title);
        Predicate<Mov> filterByYear = mov -> mov.getYear() == year;
        return new MovsDTO().insertAll(movDao.findAll()
                .stream()
                .filter(filterByTitle.and(filterByYear))
                .map(MovToDTO::mapperShort)
                .collect(Collectors.toList()));
    }

    @Transactional
    public MovDetailDTO createMov(MovInputDTO movInputDTO) {
        String validated = MovChecker.checkParamsInput(movInputDTO);
        if (!validated.equals("Valida")) {
            throw new WrongInputLongException(
                    format("La pelicula no se ha procesado por el siguiente motivo: %s", validated));
        }
        if(movDao.findByTitleAndYear(movInputDTO.getTitle(), movInputDTO.getYear()).isPresent())
            throw new DuplicatedException("La pelicula no se ha insertado porque ya existe en la base de datos");

        return saveMov(DTOToMov.mapperLong(movInputDTO));
    }

    @Transactional
    public MovDetailDTO modifyMov(String title, MovInputDTO movInputDTO) {
        String validated = MovChecker.checkParamsInput(movInputDTO);
        if (!validated.equals("Valida")) {
            throw new WrongInputLongException(
                    format("La pelicula no se ha procesado por el siguiente motivo: %s", validated));
        }
        Mov movToModify = movDao.findByTitle(title).orElseThrow(() -> new NotFoundException(
                format("La pelicula '%s' no figura en la base de datos", title)));

        return saveMov(DTOToMov.SetMovParams(movInputDTO, movToModify));
    }

    private MovDetailDTO saveMov(Mov mov) {
        return MovToDTO.mapperLong(movDao.save(mov));
    }

    public void deleteMov(Long id) {
        Mov movToDelete = movDao.findById(id).orElseThrow(() -> new NotFoundException(
                format("La pelicula '%d' no figura en la base de datos", id)));
        movDao.delete(movToDelete);
    }
}
