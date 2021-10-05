package ru.danko.testtask.services;

import ru.danko.testtask.entity.Enroll;
import ru.danko.testtask.exception.DaoServiceException;

import java.util.Map;
import java.util.Optional;

public interface EnrollService {

    Optional<Enroll> findGroupByStudentId(String idStudent) throws DaoServiceException;

}
