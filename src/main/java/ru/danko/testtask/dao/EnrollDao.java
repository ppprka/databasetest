package ru.danko.testtask.dao;

import ru.danko.testtask.entity.Enroll;

import java.util.Optional;

public interface EnrollDao {

    Optional<Enroll> findGroupByStudent(long id_Student);

    Optional<Enroll> findStudentByGroup(long id_Group);

    boolean add(Enroll enroll);

    boolean update(Enroll enroll);

    boolean delete(long id);

}
