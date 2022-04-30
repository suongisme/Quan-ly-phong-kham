package com.clinic.mapper;

import com.clinic.entity.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionMapper implements IMapper<Prescription> {

    @Override
    public Prescription mapper(ResultSet resultSet) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setCreatedDate(resultSet.getDate("ngayKham"));
        prescription.setDiseases(resultSet.getString("benhDuocChuanDoan"));
        prescription.setNumericOrder(resultSet.getInt("stt"));
        prescription.setPatientCode(resultSet.getString("maBenhNhan"));
        return prescription;
    }
}