package ru.danko.testtask.services;

import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findByNameAndSurname(String name, String surname) throws DaoServiceException;

    Optional<Student> findById(String id) throws DaoServiceException;

    Optional<Student> findByName(String name) throws DaoServiceException;

    Optional<Student> findBySurname(String surname) throws DaoServiceException;

    List<Student> findAll() throws DaoServiceException;

    boolean addStudent(Map<String,String> parameters) throws DaoServiceException;

    boolean updateStudent(String id) throws DaoServiceException;

    boolean deleteStudent(String id) throws DaoServiceException;
}
