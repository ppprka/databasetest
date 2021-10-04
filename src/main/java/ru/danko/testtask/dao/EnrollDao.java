package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Enroll;

import java.util.Optional;

public interface EnrollDao {

    Optional<Enroll> findGroupByStudent(long idStudent);

    Optional<Enroll> findStudentByGroup(long idGroup);

    boolean add(Enroll enroll);

    boolean update(Enroll enroll);

    boolean delete(long id);

}
