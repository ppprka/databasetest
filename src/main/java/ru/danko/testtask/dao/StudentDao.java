package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Optional<Student> findByNameAndSurname(String name,String surname) throws DaoServiceException;

    Optional<Student> findById(long id) throws DaoServiceException;

    Optional<Student> findByName(String name) throws DaoServiceException;

    Optional<Student> findBySurname(String surname) throws DaoServiceException;

    List<Student> findAll() throws DaoServiceException;

    boolean add(Student student) throws DaoServiceException;

    boolean update(Student student) throws DaoServiceException;

    boolean delete(long id) throws DaoServiceException;
}
