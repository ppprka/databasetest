package ru.danko.testtask.dao.impl;

public class RecordBookDaoImpl {
    private static final RecordBookDaoImpl INSTANCE = new RecordBookDaoImpl();
    private static final String FIND_RECORD_BOOK_BY_GRADUATION_YEAR = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year, studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE graduation_year=?";
    private static final String FIND_RECORD_BOOK_BY_ID = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE id=?";
    private static final String FIND_STUDENT_BY_GROUP_ID = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book WHERE group_id=?";
    private static final String FIND_ALL = "SELECT studentsdatabase.record_book.id, " +
            "studentsdatabase.record_book.graduation_year,studentsdatabase.record_book.group_id " +
            "FROM studentsdatabase.record_book";
    private static final String ADD_RECORD_BOOK = "INSERT INTO studentsdatabase.record_book (id,graduation_year,group_id) values (?,?)";
    private static final String UPDATE_RECORD_BOOK= "UPDATE studentsdatabase.student SET name=?, surname=? WHERE id=?";
    private static final String DELETE_RECORD_BOOK  = "DELETE FROM studentsdatabase.student WHERE id=?";

    private RecordBookDaoImpl() {}

    public static RecordBookDaoImpl getInstance() {

        return INSTANCE;
    }
}
