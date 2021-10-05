package ru.danko.testtask.dao;

import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.List;
import java.util.Optional;

public interface RecordBookDao {

    Optional<RecordBook> findById(long id) throws DaoServiceException;

    Optional<RecordBook> findByGraduationYear(String graduationYear) throws DaoServiceException;

    Optional<RecordBook> findByGroupId(String groupId) throws DaoServiceException;

    List<RecordBook> findAll() throws DaoServiceException;

    boolean add(RecordBook recordBook) throws DaoServiceException;

    boolean update(RecordBook recordBook) throws DaoServiceException;

    boolean delete(long id) throws DaoServiceException;
}
