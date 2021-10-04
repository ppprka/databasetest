package ru.danko.testtask.dao;

import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface StudentGroupDao {

    Optional<StudentGroup> findById(long id) throws DaoException;

    Optional<StudentGroup> findByNumber(String number) throws DaoException;

    List<StudentGroup> findAll() throws DaoException;

    boolean add(StudentGroup studentGroup) throws DaoException;

    boolean update(StudentGroup studentGroup) throws DaoException;

    boolean delete(long id) throws DaoException;
}
