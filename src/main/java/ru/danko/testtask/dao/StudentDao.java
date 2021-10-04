package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Optional<Student> findByNameAndSurname(String name,String surname) throws DaoException;

    Optional<Student> findById(long id) throws DaoException;

    Optional<Student> findByName(String name) throws DaoException;

    Optional<Student> findBySurname(String surname) throws DaoException;

    List<Student> findAll() throws DaoException;

    boolean add(Student student) throws DaoException;

    boolean update(Student student) throws DaoException;

    boolean delete(long id) throws DaoException;
}
