package com.clinic.controller;

import com.clinic.dao.MedicineDao;
import com.clinic.dao.PatientDao;
import com.clinic.dao.PrescriptionDao;
import com.clinic.dao.PrescriptionDetailDao;
import com.clinic.entity.Medicine;
import com.clinic.entity.Patient;
import com.clinic.entity.Prescription;
import com.clinic.entity.PrescriptionDetail;
import com.clinic.mapper.*;
import com.clinic.utils.Convertor;
import com.clinic.utils.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/prescription/*")
public class PrescriptionController extends HttpServlet {

    private PrescriptionDao prescriptionDao;
    private PatientDao patientDao;
    private MedicineDao medicineDao;
    private PrescriptionDetailDao prescriptionDetailDao;

    @Override
    public void init() throws ServletException {
        IMapper<Prescription> prescriptMapper = new PrescriptionMapper();
        JdbcTemplate<Prescription> jdbcTemplatePrescription = new JdbcTemplate<>(prescriptMapper);
        this.prescriptionDao = new PrescriptionDao(jdbcTemplatePrescription);

        IMapper<Patient> patientMapper = new PatientMapper();
        JdbcTemplate<Patient> jdbcTemplatePatient = new JdbcTemplate<>(patientMapper);
        this.patientDao = new PatientDao(jdbcTemplatePatient);

        IMapper<Medicine> medicineMapper = new MedicineMapper();
        JdbcTemplate<Medicine> jdbcTemplate = new JdbcTemplate<>(medicineMapper);
        this.medicineDao = new MedicineDao(jdbcTemplate);

        IMapper<PrescriptionDetail> prescriptionDetailMappep = new PrescriptionDetailMapper();
        JdbcTemplate<PrescriptionDetail> jdbcTemplateDetail = new JdbcTemplate<>(prescriptionDetailMappep);
        this.prescriptionDetailDao = new PrescriptionDetailDao(jdbcTemplateDetail);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            List<Prescription> prescriptions = this.prescriptionDao.findAll();
            req.setAttribute("prescriptions", prescriptions);
            req.getRequestDispatcher("/view/prescription/list-prescription.jsp").forward(req, resp);
            return;
        }

        switch (pathInfo) {
            case "/form":
                req.setAttribute("prescription", new Prescription());
                req.setAttribute("medicines", this.medicineDao.findAll());
                req.setAttribute("patients", this.patientDao.findAll());
                req.setAttribute("readOnly", false);
                req.getRequestDispatcher("/view/prescription/form-prescription.jsp").forward(req, resp);
                break;
            case "/view":
                String numericOrder = req.getParameter("numericOrder");
                Integer integer = Integer.valueOf(numericOrder);
                Optional<Prescription> prescriptionOpt = this.prescriptionDao.findByOr(integer);
                Prescription prescription = prescriptionOpt.get();
                Optional<Patient> patientOpt = this.patientDao.findByCode(prescription.getPatientCode());
                List<PrescriptionDetail> prescriptionDetails = this.prescriptionDetailDao.findByNumericOrder(integer);
                List<String> medicineCodes = prescriptionDetails.stream().map(detail -> detail.getMedicineCode()).collect(Collectors.toList());
                List<Medicine> medicines = this.medicineDao.findAllByCode(medicineCodes);

                req.setAttribute("readOnly", true);
                req.setAttribute("prescription", prescription);
                req.setAttribute("medicines", medicines);
                req.setAttribute("patients", Arrays.asList( patientOpt.get() ));
                req.getRequestDispatcher("/view/prescription/form-prescription.jsp").forward(req, resp);
                break;
            case "/search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("prescriptions", this.prescriptionDao.doSearch(keyword));
                req.getRequestDispatcher("/view/prescription/list-prescription.jsp").forward(req, resp);
                break;
            default:
                List<Prescription> prescriptions = this.prescriptionDao.findAll();
                req.setAttribute("prescriptions", prescriptions);
                req.getRequestDispatcher("/view/prescription/list-prescription.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            return;
        }

        switch (pathInfo) {
            case "/form":
                String[] medicines = req.getParameterValues("medicineCode");
                Prescription prescription = Convertor.requestParamToObject(req, Prescription.class);
                this.prescriptionDao.insert(prescription);
                List<Prescription> prescriptions = this.prescriptionDao.findAll();
                prescription = prescriptions.get(prescriptions.size() - 1);
                PrescriptionDetail detail = Convertor.requestParamToObject(req, PrescriptionDetail.class);
                for (String medicine : medicines) {
                    PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
                    prescriptionDetail.setMedicineCode(medicine);
                    prescriptionDetail.setDosage(detail.getDosage());
                    prescriptionDetail.setNumericOrder(prescription.getNumericOrder());
                    this.prescriptionDetailDao.insert(prescriptionDetail);
                }
                resp.sendRedirect(req.getContextPath()+"/prescription");
                break;
        }

    }

}