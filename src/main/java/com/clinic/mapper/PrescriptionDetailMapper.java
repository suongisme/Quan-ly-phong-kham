package com.clinic.mapper;

import com.clinic.entity.PrescriptionDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDetailMapper implements IMapper<PrescriptionDetail> {

    @Override
    public PrescriptionDetail mapper(ResultSet resultSet) throws SQLException {
        PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
        prescriptionDetail.setDosage(resultSet.getString("lieuDung"));
        prescriptionDetail.setMedicineCode(resultSet.getString("maThuoc"));
        prescriptionDetail.setNumericOrder(resultSet.getInt("stt"));
        return prescriptionDetail;
    }
}