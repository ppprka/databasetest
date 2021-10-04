package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.StudentGroupDao;
import ru.danko.testtask.dao.connection.DbcpDataSource;
import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentGroupDaoImpl implements StudentGroupDao{
    private static final StudentGroupDaoImpl INSTANCE = new StudentGroupDaoImpl();
    private static final String FIND_STUDENT_GROUP_BY_ID = "SELECT studentsdatabase.student_group.id, " +
            "studentsdatabase.student_group.number FROM studentsdatabase.student_group WHERE id=?";
    private static final String FIND_STUDENT_GROUP_BY_NUMBER = "SELECT studentsdatabase.student_group.id, " +
            "studentsdatabase.student_group.number FROM studentsdatabase.student_group WHERE number=?";
    private static final String FIND_ALL = "SELECT studentsdatabase.student_group.id, " +
            "studentsdatabase.student_group.number FROM studentsdatabase.student_group ";
    private static final String ADD_STUDENT_GROUP = "INSERT INTO studentsdatabase.student_group (number) values (?)";
    private static final String UPDATE_STUDENT_GROUP= "UPDATE studentsdatabase.student_group SET number=? WHERE id=?";
    private static final String DELETE_STUDENT_GROUP  = "DELETE FROM studentsdatabase.student_group WHERE id=?";

    private StudentGroupDaoImpl() {}

    public static StudentGroupDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<StudentGroup> findById(long id) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_GROUP_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<StudentGroup> studentGroupOptional = Optional.empty();
            if (resultSet.next()) {
                StudentGroup studentGroup = createStudentGroupFromResultSet(resultSet);
                studentGroupOptional = Optional.of(studentGroup);
            }
            return studentGroupOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding student group by id error", e);
        }
    }

    @Override
    public Optional<StudentGroup> findByNumber(String number) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_GROUP_BY_NUMBER);
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            Optional<StudentGroup> studentGroupOptional = Optional.empty();
            if (resultSet.next()) {
                StudentGroup studentGroup = createStudentGroupFromResultSet(resultSet);
                studentGroupOptional = Optional.of(studentGroup);
            }
            return studentGroupOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding student group by number error", e);
        }
    }

    @Override
    public List<StudentGroup> findAll() throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            List<StudentGroup> studentGroupList = new ArrayList<>();
            if (resultSet.next()) {
                StudentGroup studentGroup = createStudentGroupFromResultSet(resultSet);
                studentGroupList.add(studentGroup);
            }
            return studentGroupList;
        } catch (SQLException e) {
            throw new DaoException("Finding student groups error", e);
        }
    }

    @Override
    public boolean add(StudentGroup studentGroup) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_STUDENT_GROUP)) {
            statement.setString(1,studentGroup.getNumber());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Add student group error", e);
        }
    }

    @Override
    public boolean update(StudentGroup studentGroup) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_GROUP)) {
            statement.setString(1, studentGroup.getNumber());
            statement.setLong(2,studentGroup.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update student group error", e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_GROUP)) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Delete student group error", e);
        }
    }

    private StudentGroup createStudentGroupFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String number = resultSet.getString(2);
        return new StudentGroup(id, number);
    }
}
