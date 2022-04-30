package com.clinic.dao;

import com.clinic.entity.Medicine;
import com.clinic.utils.DataUtils;
import com.clinic.utils.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MedicineDao {
    private JdbcTemplate<Medicine> jdbcTemplate;

    public MedicineDao(JdbcTemplate<Medicine> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Medicine> findByCode(String code) {
        return this.jdbcTemplate
                .query("SELECT * FROM Thuoc WHERE maThuoc = ?", code)
                .executeQuery();
    }

    public List<Medicine> doSearch(String keyword) {
        keyword = DataUtils.resolveKeySearch(keyword);
        return this.jdbcTemplate
                .query("SELECT * FROM Thuoc WHERE maThuoc LIKE ? OR tenThuoc LIKE ?", keyword, keyword)
                .executeQueryList();
    }

    public List<Medicine> findAllByCode(List<String> codes) {
        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM Thuoc WHERE maThuoc IN (");
        for (String code : codes) {
            stringBuilder.append(", ?");
        }
        stringBuilder.append(")");
        String sql = stringBuilder.toString().replaceFirst(",", "");
        return this.jdbcTemplate
                .query(sql, codes.toArray())
                .executeQueryList();
    }

    public List<Medicine> findAll() {
        return this.jdbcTemplate
                .query("SELECT * FROM Thuoc")
                .executeQueryList();
    }

    public int insert(Medicine medicine) {
        String sqlInsert = "INSERT INTO Thuoc(maThuoc, tenThuoc, nuocSanXuat, giaTien, hanSuDung, moTa) VALUES(?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{medicine.getCode(), medicine.getName(), medicine.getCountry(), medicine.getPrice(), medicine.getExpire(), medicine.getDescription()};
        return this.jdbcTemplate
                .save(sqlInsert, params)
                .executeSave();
    }

    public int update(String code, Medicine medicine) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE Thuoc SET maThuoc = maThuoc");
        List<Object> params = new ArrayList<>();

        if (Objects.nonNull(medicine.getName())) {
            sqlUpdate.append(", tenThuoc = ?");
            params.add(medicine.getName());
        }

        if (Objects.nonNull(medicine.getCountry())) {
            sqlUpdate.append(", nuocSanXuat = ?");
            params.add(medicine.getCountry());
        }

        if (Objects.nonNull(medicine.getDescription())) {
            sqlUpdate.append(", moTa = ?");
            params.add(medicine.getDescription());
        }

        if (Objects.nonNull(medicine.getExpire())) {
            sqlUpdate.append(", hanSuDung = ?");
            params.add(medicine.getExpire());
        }

        if (Objects.nonNull(medicine.getPrice())) {
            sqlUpdate.append(", giaTien = ?");
            params.add(medicine.getPrice());
        }

        sqlUpdate.append(" WHERE maThuoc = ?");
        params.add(code);
        return this.jdbcTemplate
                .save(sqlUpdate.toString(), params.toArray())
                .executeSave();
    }

    public int delete(String code) {
        return this.jdbcTemplate
                .delete("DELETE FROM Medicine WHERE code = ?", code)
                .executeDelete();
    }
}