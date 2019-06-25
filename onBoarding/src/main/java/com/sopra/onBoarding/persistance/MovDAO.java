package com.sopra.onBoarding.persistance;

import com.sopra.onBoarding.entities.Mov;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MovDAO extends CrudRepository<Mov, Long> {

    Optional<Mov> findByTitle(String title);
    Optional<Mov> findByTitleAndYear(String title, int year);
    List<Mov> findAll();
}
