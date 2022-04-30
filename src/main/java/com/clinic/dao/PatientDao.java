package com.clinic.dao;

import com.clinic.entity.Patient;
import com.clinic.utils.DataUtils;
import com.clinic.utils.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PatientDao {

    private JdbcTemplate<Patient> jdbcTemplate;

    public PatientDao(JdbcTemplate<Patient> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Patient> findByCode(String code) {
        return this.jdbcTemplate
                .query("SELECT * FROM BenhNhan WHERE maBenhNhan = ?", code)
                .executeQuery();
    }

    public List<Patient> doSearch(String keyword) {
        keyword = DataUtils.resolveKeySearch(keyword);
        return this.jdbcTemplate
                .query("SELECT * FROM BenhNhan WHERE maBenhNhan LIKE ? OR tenBenhNhan LIKE ?", keyword, keyword)
                .executeQueryList();
    }

    public List<Patient> findAll() {
        return this.jdbcTemplate
                .query("SELECT * FROM BenhNhan")
                .executeQueryList();
    }

    public int insert(Patient patient) {
        String sqlInsert = "INSERT INTO BenhNhan(maBenhNhan, tenBenhNhan, tuoi, gioiTinh, diaChi) VALUES(?, ?, ?, ?, ?)";
        Object[] params = new Object[]{patient.getCode(), patient.getName(), patient.getAge(), patient.getGender(), patient.getAddress()};
        return this.jdbcTemplate
                .save(sqlInsert, params)
                .executeSave();
    }

    public int update(String code, Patient patient) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE BenhNhan SET maBenhNhan = maBenhNhan");
        List<Object> params = new ArrayList<>();

        if (Objects.nonNull(patient.getName())) {
            sqlUpdate.append(", tenBenhNhan = ?");
            params.add(patient.getName());
        }

        if (Objects.nonNull(patient.getAddress())) {
            sqlUpdate.append(", diaChi = ?");
            params.add(patient.getAddress());
        }

        if (Objects.nonNull(patient.getAge())) {
            sqlUpdate.append(", tuoi = ?");
            params.add(patient.getAge());
        }

        if (Objects.nonNull(patient.getGender())) {
            sqlUpdate.append(", gioiTinh = ?");
            params.add(patient.getGender());
        }
        sqlUpdate.append(" WHERE maBenhNhan = ?");
        params.add(code);
        return this.jdbcTemplate
                .save(sqlUpdate.toString(), params.toArray())
                .executeSave();
    }

    public int delete(String code) {
        return this.jdbcTemplate
                .delete("DELETE FROM BenhNhan WHERE maBenhNhan = ?", code)
                .executeDelete();
    }
}