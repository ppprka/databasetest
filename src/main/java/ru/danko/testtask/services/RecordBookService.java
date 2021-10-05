package ru.danko.testtask.services;

import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RecordBookService {
    Optional<RecordBook> findById(String id) throws DaoServiceException;

    Optional<RecordBook> findByGraduationYear(String graduationYear) throws DaoServiceException;

    Optional<RecordBook> findByGroupId(String groupId) throws DaoServiceException;

    List<RecordBook> findAll() throws DaoServiceException;

    boolean add(Map<String, String> parameters) throws DaoServiceException;

    boolean update(String id) throws DaoServiceException;

    boolean delete(String id) throws DaoServiceException;
}
