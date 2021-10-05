package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.connection.DbcpDataSource;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.exception.DaoServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private static final StudentDaoImpl INSTANCE = new StudentDaoImpl();
    private static final String FIND_STUDENT_BY_NAME_AND_SURNAME = "SELECT studentsdatabase.student.id, " +
            "studentsdatabase.student.name, studentsdatabase.student.surname " +
            "FROM studentsdatabase.student WHERE name=? AND surname=? ORDER BY surname DESC";
    private static final String FIND_STUDENT_BY_ID = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE id=? ORDER BY surname DESC";
    private static final String FIND_STUDENT_BY_NAME = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE name=? ORDER BY surname DESC";
    private static final String FIND_STUDENT_BY_SURNAME = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE surname=? ORDER BY surname DESC";
    private static final String FIND_ALL = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student ORDER BY surname DESC";
    private static final String ADD_STUDENT = "INSERT INTO studentsdatabase.student (name,surname) values (?,?)";
    private static final String UPDATE_STUDENT = "UPDATE studentsdatabase.student SET name=?, surname=? WHERE id=?";
    private static final String DELETE_STUDENT  = "DELETE FROM studentsdatabase.student WHERE id=?";

    private StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Student> findByNameAndSurname(String name, String surname) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_NAME_AND_SURNAME);
            statement.setString(1, name);
            statement.setString(2, surname);
            ResultSet resultSet = statement.executeQuery();
            Optional<Student> studentOptional = Optional.empty();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentOptional = Optional.of(student);
            }
            return studentOptional;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding student by email and password error", e);
        }
    }

    @Override
    public Optional<Student> findById(long id) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<Student> studentOptional = Optional.empty();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentOptional = Optional.of(student);
            }
            return studentOptional;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding student by id error", e);
        }
    }
    @Override
    public Optional<Student> findByName(String name) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Optional<Student> studentOptional = Optional.empty();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentOptional = Optional.of(student);
            }
            return studentOptional;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding student by name error", e);
        }
    }

    @Override
    public Optional<Student> findBySurname(String surname) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_BY_SURNAME);
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            Optional<Student> studentOptional = Optional.empty();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentOptional = Optional.of(student);
            }
            return studentOptional;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding student by surname error", e);
        }
    }

    @Override
    public List<Student> findAll() throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding all students error", e);
        }
    }

    @Override
    public boolean add(Student student) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENT)) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoServiceException("Add student error", e);
        }
    }

    @Override
    public boolean update(Student student) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setLong(3,student.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoServiceException("Update student error", e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoServiceException("Delete student error", e);
        }
    }

    private Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        return new Student(id, name, surname);
    }
}

