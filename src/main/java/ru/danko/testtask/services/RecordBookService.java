package ru.danko.testtask.services;

import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface RecordBookService {
    Optional<RecordBook> findById(long id) throws DaoException;

    Optional<RecordBook> findByGraduationYear(String graduationYear) throws DaoException;

    Optional<RecordBook> findByGroupId(String groupId) throws DaoException;

    List<RecordBook> findAll() throws DaoException;

    boolean add(RecordBook recordBook) throws DaoException;

    boolean update(RecordBook recordBook) throws DaoException;

    boolean delete(long id) throws DaoException;
}
