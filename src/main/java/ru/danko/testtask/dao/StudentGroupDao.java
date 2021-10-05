package ru.danko.testtask.dao;

import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Optional;

public interface StudentGroupDao {

    Optional<StudentGroup> findById(long id) throws DaoServiceException;

    Optional<StudentGroup> findByNumber(String number) throws DaoServiceException;

    List<StudentGroup> findAll() throws DaoServiceException;

    boolean add(StudentGroup studentGroup) throws DaoServiceException;

    boolean update(StudentGroup studentGroup) throws DaoServiceException;

    boolean delete(long id) throws DaoServiceException;
}
