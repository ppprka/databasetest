package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.EnrollDao;
import ru.danko.testtask.dao.connection.DbcpDataSource;
import ru.danko.testtask.entity.Enroll;
import ru.danko.testtask.entity.Student;
import ru.danko.testtask.entity.StudentGroup;
import ru.danko.testtask.exception.DaoServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EnrollDaoImpl implements EnrollDao {
    private static final EnrollDaoImpl INSTANCE = new EnrollDaoImpl();
    private static final String FIND_ENROLL_BY_STUDENT_ID = "SELECT studentsdatabase.enroll.id," +
            "studentsdatabase.enroll.id_group,studentsdatabase.enroll.id_student FROM studentsdatabase.enroll " +
            "WHERE id_student=?";
    private static final String ADD_ENROLL = "INSERT INTO studentsdatabase.enroll (id_student,id_group) values (?,?)";
    private static final String DELETE_ENROLL = "DELETE FROM studentsdatabase.enroll WHERE id=?";

    private EnrollDaoImpl() {}

    public static EnrollDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<Enroll> findGroupByStudentId(long idStudent) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ENROLL_BY_STUDENT_ID);
            statement.setLong(1, idStudent);
            ResultSet resultSet = statement.executeQuery();
            Optional<Enroll> enrollOptional = Optional.empty();
            if (resultSet.next()) {
                Enroll enroll = createEnrollFromResultSet(resultSet);
                enrollOptional = Optional.of(enroll);
            }
            return enrollOptional;
        } catch (SQLException e) {
            throw new DaoServiceException("Finding student group by id error", e);
        }
    }

    @Override
    public boolean add(Enroll enroll) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ENROLL)) {
            statement.setLong(1,enroll.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoServiceException("Add student group error", e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoServiceException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ENROLL)) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoServiceException("Delete student group error", e);
        }
    }

    private Enroll createEnrollFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        Long idStudent = resultSet.getLong(2);
        Long idGroup = resultSet.getLong(3);
        Student student = new Student();
        student.setId(idStudent);
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setId(idGroup);
        return new Enroll(id, student, studentGroup);
    }
}
