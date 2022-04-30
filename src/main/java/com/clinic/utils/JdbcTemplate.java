package com.clinic.utils;

import com.clinic.config.Database;
import com.clinic.mapper.IMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JdbcTemplate<T> {

    private ResultSet resultSet;
    private IMapper<T> mapper;
    private PreparedStatement preparedStatement;

    public JdbcTemplate(IMapper<T> mapper) {
        this.mapper = mapper;
    }

    public JdbcTemplate query(String sql, Object...params) {
        resultSet = Database.query(sql, params);
        return this;
    }

    public JdbcTemplate save(String sql, Object... params) {
        this.preparedStatement = Database.save(sql, params);
        return this;
    }


    public JdbcTemplate delete(String sql, Object... params) {
        return this.save(sql, params);
    }

    public int executeDelete() {
        return this.executeSave();
    }

    public int executeSave() {
        try {
            if (Objects.isNull(preparedStatement)) {
                throw new RuntimeException("PreparedStatement cannot be null");
            }
            return this.preparedStatement.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            Database.closeConnection();
        }
    }

    public Optional<T> executeQuery() {
        try {
            if (Objects.isNull(this.resultSet))
                throw new RuntimeException("Result cannot be null");
            this.resultSet.next();
            return Optional.ofNullable(this.mapper.mapper(resultSet));
        } catch(Exception ex) {
            return Optional.ofNullable(null);
        } finally {
            Database.closeConnection();
        }
    }

    public List<T> executeQueryList() {
        try {
            if (Objects.isNull(this.resultSet)) {
                throw new RuntimeException("ResultSet cannot be null");
            }
            return this.mapper.mapperList(this.resultSet);
        } catch(Exception ex) {
            return new ArrayList<>();
        } finally {
            Database.closeConnection();
        }
    }
}