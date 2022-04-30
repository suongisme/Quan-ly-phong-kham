package com.clinic.dao;

import com.clinic.entity.Patient;
import com.clinic.entity.Prescription;
import com.clinic.utils.DataUtils;
import com.clinic.utils.JdbcTemplate;

import java.util.*;

public class PrescriptionDao {

    private JdbcTemplate<Prescription> jdbcTemplate;

    public PrescriptionDao(JdbcTemplate<Prescription> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Prescription> findAll() {
        return this.jdbcTemplate
                .query("SELECT * FROM DonThuoc")
                .executeQueryList();
    }

    public List<Prescription> doSearch(String keyword) {
        keyword = DataUtils.resolveKeySearch(keyword);
        return this.jdbcTemplate
                .query("SELECT * FROM DonThuoc WHERE maBenhNhan LIKE ?", keyword)
                .executeQueryList();
    }

    public Optional<Prescription> findByOr(Integer ord) {
        String sql = "SELECT * FROM DonThuoc WHERE stt = ?";
        return this.jdbcTemplate.query(sql, ord)
                .executeQuery();
    }

    public int insert(Prescription prescription) {
        String sqlInsert = "INSERT INTO DonThuoc(maBenhNhan, benhDuocChuanDoan, ngayKham) VALUES(?, ?, ?)";
        prescription.setCreatedDate(new Date());
        Object[] params = new Object[]{prescription.getPatientCode(), prescription.getDiseases(), prescription.getCreatedDate()};
        return this.jdbcTemplate
                .save(sqlInsert, params)
                .executeSave();
    }
}