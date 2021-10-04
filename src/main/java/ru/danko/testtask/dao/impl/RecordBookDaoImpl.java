package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.RecordBookDao;
import ru.danko.testtask.dao.connection.DbcpDataSource;
import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecordBookDaoImpl implements RecordBookDao {
    private static final RecordBookDaoImpl INSTANCE = new RecordBookDaoImpl();
    private static final String FIND_RECORD_BOOK_BY_GRADUATION_YEAR = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year, studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE graduation_year=?";
    private static final String FIND_RECORD_BOOK_BY_ID = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE id=?";
    private static final String FIND_RECORD_BOOK_BY_GROUP_ID = "SELECT studentsdatabase.record_book.id," +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE group_id=?";
    private static final String FIND_ALL = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book";
    private static final String ADD_RECORD_BOOK = "INSERT INTO studentsdatabase.record_book (id,graduation_year,group_id) values (?,?,?)";
    private static final String UPDATE_RECORD_BOOK= "UPDATE studentsdatabase.record_book SET graduation_year=?, group_id=? WHERE id=?";
    private static final String DELETE_RECORD_BOOK  = "DELETE FROM studentsdatabase.record_book WHERE id=?";
    private static final String FIND_BY_SAME_GRADUATION_YEAR = "SELECT studentsdatabase.student.id,studentsdatabase.student.name, " +
            "studentsdatabase.student.surname " +
            "FROM studentsdatabase.record_book JOIN studentsdatabase.student s on s.id = record_book.id " +
            "WHERE studentsdatabase.record_book.graduation_year>?";
    private static final String FIND_BY_COUNT_STUDENTS_IN_GROUP = "SELECT studentsdatabase.student_group.id," +
            "studentsdatabase.student_group.number FROM studentsdatabase.student_group JOIN studentsdatabase.record_book rb on " +
            "student_group.id = rb.group_id HAVING COUNT(studentsdatabase.record_book.group_id)>?";

    private RecordBookDaoImpl() {}

    public static RecordBookDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<RecordBook> findById(long id) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_RECORD_BOOK_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Optional<RecordBook> recordBookOptional = Optional.empty();
            if (resultSet.next()) {
                RecordBook recordBook = createRecordBookFromResultSet(resultSet);
                recordBookOptional = Optional.of(recordBook);
            }
            return recordBookOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding record book by id error", e);
        }
    }

    @Override
    public Optional<RecordBook> findByGraduationYear(String graduationYear) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_RECORD_BOOK_BY_GRADUATION_YEAR);
            statement.setString(1, graduationYear);
            ResultSet resultSet = statement.executeQuery();
            Optional<RecordBook> recordBookOptional = Optional.empty();
            if (resultSet.next()) {
                RecordBook recordBook = createRecordBookFromResultSet(resultSet);
                recordBookOptional = Optional.of(recordBook);
            }
            return recordBookOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding record book by graduation year error", e);
        }
    }

    @Override
    public Optional<RecordBook> findByGroupId(String groupId) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_RECORD_BOOK_BY_GROUP_ID);
            statement.setString(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            Optional<RecordBook> recordBookOptional = Optional.empty();
            if (resultSet.next()) {
                RecordBook recordBook = createRecordBookFromResultSet(resultSet);
                recordBookOptional = Optional.of(recordBook);
            }
            return recordBookOptional;
        } catch (SQLException e) {
            throw new DaoException("Finding record book by group id error", e);
        }
    }

    @Override
    public List<RecordBook> findAll() throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = statement.executeQuery();
            List<RecordBook> recordBookList = new ArrayList<>();
            if (resultSet.next()) {
                RecordBook recordBook = createRecordBookFromResultSet(resultSet);
                recordBookList.add(recordBook);
            }
            return recordBookList;
        } catch (SQLException e) {
            throw new DaoException("Finding record book error", e);
        }
    }

    @Override
    public boolean add(RecordBook recordBook) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_RECORD_BOOK)) {
            statement.setLong(1,recordBook.getId());
            statement.setString(2,recordBook.getGraduationYear());
            statement.setString(3, recordBook.getGroup_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Add record book error", e);
        }
    }

    @Override
    public boolean update(RecordBook recordBook) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECORD_BOOK)) {
            statement.setString(1, recordBook.getGraduationYear());
            statement.setString(2, recordBook.getGroup_id());
            statement.setLong(3,recordBook.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Update record book error", e);
        }
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECORD_BOOK)) {
            statement.setLong(1,id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Delete record book error", e);
        }
    }

    public void findBySameGraduationYear(String graduationYear) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_SAME_GRADUATION_YEAR);
            statement.setString(1, graduationYear);
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                System.out.println("Id - "+id+", Name - "+name+", Surname - "+surname);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding record book by id error", e);
        }
    }

    public void findByCountStudentsInGroup(String num) throws DaoException {
        try (Connection connection = DbcpDataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_BY_COUNT_STUDENTS_IN_GROUP);
            statement.setString(1, num);
            ResultSet resultSet = statement.executeQuery();
            int count = 0;
            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String number = resultSet.getString(2);
                System.out.println("Id - "+id+", Number - "+number);
            }
        } catch (SQLException e) {
            throw new DaoException("Finding record book by id error", e);
        }
    }

    private RecordBook createRecordBookFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String graduationYear = resultSet.getString(2);
        String groupId = resultSet.getString(3);
        return new RecordBook(id, graduationYear, groupId);
    }
}
