package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Optional<Student> findByNameAndSurname(String name,String surname) throws SQLException;

    Optional<Student> findById(long id) throws SQLException;

    Optional<Student> findByName(String name) throws SQLException;

    Optional<Student> findBySurname(String surname) throws SQLException;

    List<Student> findAll() throws SQLException;

    boolean add(Student student) throws SQLException;

    boolean update(Student student) throws SQLException;

    boolean delete(long id) throws SQLException;
}
