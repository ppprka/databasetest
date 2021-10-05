package ru.danko.testtask.dao.impl;

import ru.danko.testtask.dao.connection.DbcpDataSource;
import ru.danko.testtask.entity.RecordBook;
import ru.danko.testtask.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomDaoImpl {
    private static final CustomDaoImpl INSTANCE = new CustomDaoImpl();


    private CustomDaoImpl() {}

    public static CustomDaoImpl getInstance() {
        return INSTANCE;
    }


}
