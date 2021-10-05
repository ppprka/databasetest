package ru.danko.testtask.services;

import ru.danko.testtask.entity.Enroll;
import ru.danko.testtask.exception.DaoException;

import java.util.Optional;

public interface EnrollService {

    Optional<Enroll> findGroupByStudentId(long idStudent) throws DaoException;

    boolean add(Enroll enroll) throws DaoException;

    boolean delete(long id) throws DaoException;
}
