package com.clinic.mapper;

import com.clinic.entity.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMapper implements IMapper<Patient> {

    @Override
    public Patient mapper(ResultSet resultSet) throws SQLException {
        Patient patient = new Patient();
        patient.setAddress(resultSet.getString("diaChi"));
        patient.setAge(resultSet.getInt("tuoi"));
        patient.setCode(resultSet.getString("maBenhNhan"));
        patient.setGender(resultSet.getInt("gioiTinh"));
        patient.setName(resultSet.getString("tenBenhNhan"));
        return patient;
    }
}