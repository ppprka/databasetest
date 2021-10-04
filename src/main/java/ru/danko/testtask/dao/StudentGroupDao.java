package ru.danko.testtask.dao;

import ru.danko.testtask.entity.StudentGroup;
import java.util.Optional;

public interface StudentGroupDao {

    Optional<StudentGroup> findById(long id);

    Optional<StudentGroup> findByNumber(String number);

    Optional<StudentGroup> findAll();

    boolean add(StudentGroup studentGroup);

    boolean update(StudentGroup studentGroup);

    boolean delete(long id);
}
