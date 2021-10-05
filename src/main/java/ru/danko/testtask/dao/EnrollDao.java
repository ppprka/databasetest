package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Enroll;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.Optional;

public interface EnrollDao {

    Optional<Enroll> findGroupByStudentId(long idStudent) throws DaoServiceException;

    boolean add(Enroll enroll) throws DaoServiceException;

    boolean delete(long id) throws DaoServiceException;

}
