package com.clinic.dao;

import com.clinic.entity.Patient;
import com.clinic.entity.Prescription;
import com.clinic.entity.PrescriptionDetail;
import com.clinic.utils.DataUtils;
import com.clinic.utils.JdbcTemplate;

import java.util.List;

public class PrescriptionDetailDao {

    private JdbcTemplate<PrescriptionDetail> jdbcTemplate;

    public PrescriptionDetailDao(JdbcTemplate<PrescriptionDetail> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public List<PrescriptionDetail> findAll() {
        return this.jdbcTemplate
                .query("SELECT * FROM DonThuocChiTiet")
                .executeQueryList();
    }

    public List<PrescriptionDetail> findByNumericOrder(Integer ord) {
        String sql = "SELECT * FROM DonThuocChiTiet WHERE stt = ?";
        return this.jdbcTemplate.query(sql, ord)
                .executeQueryList();
    }

    public int insert(PrescriptionDetail prescriptionDetail) {
        String sqlInsert = "INSERT INTO DonThuocChiTiet(maThuoc, lieuDung, stt) VALUES(?, ?, ?)";
        Object[] params = new Object[]{prescriptionDetail.getMedicineCode(), prescriptionDetail.getDosage(), prescriptionDetail.getNumericOrder()};
        return this.jdbcTemplate
                .save(sqlInsert, params)
                .executeSave();
    }

}