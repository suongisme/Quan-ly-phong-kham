package com.clinic.mapper;

import com.clinic.entity.Medicine;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineMapper implements IMapper<Medicine> {

    @Override
    public Medicine mapper(ResultSet resultSet) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setCode(resultSet.getString("maThuoc"));
        medicine.setName(resultSet.getString("tenThuoc"));
        medicine.setCountry(resultSet.getString("nuocSanXuat"));
        medicine.setDescription(resultSet.getString("moTa"));
        medicine.setExpire(resultSet.getDate("hanSuDung"));
        medicine.setPrice(resultSet.getDouble("giaTien"));
        return medicine;
    }
}