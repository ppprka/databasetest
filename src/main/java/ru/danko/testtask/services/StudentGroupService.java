package ru.danko.testtask.services;

import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudentGroupService {
    Optional<StudentGroup> findById(String id) throws DaoServiceException;

    Optional<StudentGroup> findByNumber(String number) throws DaoServiceException;

    List<StudentGroup> findAll() throws DaoServiceException;

    boolean add(Map<String, String> parameters) throws DaoServiceException;

    boolean update(String id) throws DaoServiceException;

    boolean delete(String  id) throws DaoServiceException;
}
