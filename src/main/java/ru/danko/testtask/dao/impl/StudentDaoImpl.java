package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.StudentDao;
import ru.danko.testtask.dao.connection.DBCPDataSource;
import ru.danko.testtask.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private static final StudentDaoImpl INSTANCE = new StudentDaoImpl();
    private static final String FIND_STUDENT_BY_NAME_AND_SURNAME = "SELECT studentsdatabase.student.id, " +
            "studentsdatabase.student.name, studentsdatabase.student.surname " +
            "FROM studentsdatabase.student WHERE name=? AND surname=?";
    private static final String FIND_STUDENT_BY_ID = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE id=?";
    private static final String FIND_STUDENT_BY_NAME = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE name=?";
    private static final String FIND_STUDENT_BY_SURNAME = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student WHERE surname=?";
    private static final String FIND_ALL = "SELECT studentsdatabase.student.id, studentsdatabase.student.name," +
            "studentsdatabase.student.surname FROM studentsdatabase.student";
    private static final String ADD_STUDENT = "INSERT INTO studentsdatabase.student (name,surname) values (?,?)";
    private static final String UPDATE_STUDENT = "UPDATE studentsdatabase.student SET name=?, surname=? WHERE id=?";
    private static final String DELETE_STUDENT  = "DELETE FROM studentsdatabase.student WHERE id=?";

    private StudentDaoImpl() {
    }

    public static StudentDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Student> findByNameAndSurname(String name, String surname) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
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
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public Optional<Student> findById(long id) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
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
            throw new SQLException("Finding user by email and password error", e);
        }
    }
    @Override
    public Optional<Student> findByName(String name) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
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
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public Optional<Student> findBySurname(String surname) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
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
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public List<Student> findAll() throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            List<Student> studentList = new ArrayList<>();
            if (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                studentList.add(student);
            }
            return studentList;
        } catch (SQLException e) {
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public boolean add(Student student) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENT)) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public boolean update(Student student) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
            statement.setString(1,student.getName());
            statement.setString(2,student.getSurname());
            statement.setLong(3,student.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new SQLException("Finding user by email and password error", e);
        }
    }

    public Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        return new Student(id, name, surname);
    }
}
