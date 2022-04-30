package com.clinic.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IMapper<T> {
    T mapper(ResultSet resultSet) throws SQLException;

    default List<T> mapperList(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            T mapper = this.mapper(resultSet);
            list.add(mapper);
        }
        return list;
    };
}


