package ru.danko.testtask.dao;

import ru.danko.testtask.entity.RecordBook;
import java.util.Optional;

public interface RecordBookDao {

    Optional<RecordBook> findById(long id);

    Optional<RecordBook> findByGraduationYear(String graduation_year);

    Optional<RecordBook> findByGroupId(String id);

    Optional<RecordBook> findAll();

    boolean add(RecordBook recordBook);

    boolean update(RecordBook recordBook);

    boolean delete(long id);
}
