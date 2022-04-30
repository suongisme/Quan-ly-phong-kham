package com.clinic.controller;

import com.clinic.dao.PatientDao;
import com.clinic.entity.Patient;
import com.clinic.mapper.IMapper;
import com.clinic.mapper.PatientMapper;
import com.clinic.utils.Convertor;
import com.clinic.utils.JdbcTemplate;
import com.clinic.utils.SystemUtils;
import com.clinic.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/patient/*")
public class PatientController extends HttpServlet {

    private PatientDao patientDao;

    @Override
    public void init() throws ServletException {
        IMapper<Patient> patientMapper = new PatientMapper();
        JdbcTemplate<Patient> jdbcTemplate = new JdbcTemplate<>(patientMapper);
        this.patientDao = new PatientDao(jdbcTemplate);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            List<Patient> patients = this.patientDao.findAll();
            req.setAttribute("patients", patients);
            req.getRequestDispatcher("/view/patient/list-patient.jsp").forward(req, resp);
            return;
        }

        switch (pathInfo) {
            case "/delete":
                String code = req.getParameter("code");
                Optional<Patient> patientOptional = this.patientDao.findByCode(code);
                if (!patientOptional.isPresent()) {
                    throw new RuntimeException(code + " dont exist");
                }
                this.patientDao.delete(code);
                resp.sendRedirect(req.getContextPath()+"/patient");
                break;
            case "/form":
                req.setAttribute("patient", new Patient());
                req.getRequestDispatcher("/view/patient/form-patient.jsp").forward(req, resp);
                break;
            case "/update":
                String medicineCode = req.getParameter("code");
                Optional<Patient> patientOpt = this.patientDao.findByCode(medicineCode);
                req.setAttribute("patient", patientOpt.orElseThrow(() -> new RuntimeException("not found medicine: "+medicineCode)));
                req.getRequestDispatcher("/view/patient/form-patient.jsp").forward(req, resp);
                break;
            case "/search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("patients", this.patientDao.doSearch(keyword));
                req.getRequestDispatcher("/view/patient/list-patient.jsp").forward(req, resp);
                break;
            default:
                List<Patient> patients = this.patientDao.findAll();
                req.setAttribute("patients", patients);
                req.getRequestDispatcher("/view/patient/list-patient.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = Convertor.requestParamToObject(req, Patient.class);
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            return;
        }

        switch (pathInfo) {
            case "/form":
                Optional<Patient> patientOpt = this.patientDao.findByCode(patient.getCode());
                if (patientOpt.isPresent()) {
                    req.setAttribute("error", patientOpt.get().getCode()+ " exist");
                    req.getRequestDispatcher("/view/patient/form-patient.jsp").forward(req, resp);
                    return;
                }
                String message = Validator.validate(patient);
                if (Objects.nonNull(message)) {
                    req.setAttribute("error", message);
                    req.getRequestDispatcher("/view/patient/form-patient.jsp").forward(req, resp);
                    return;
                }
                this.patientDao.insert(patient);
                resp.sendRedirect(req.getContextPath()+"/patient");
                break;
            case "/update":
                String messageUpdate = Validator.validate(patient);
                if (Objects.nonNull(messageUpdate)) {
                    req.setAttribute("error", messageUpdate);
                    req.getRequestDispatcher("/view/patient/form-patient.jsp").forward(req, resp);
                    return;
                }
                this.patientDao.update(patient.getCode(), patient);
                resp.sendRedirect(req.getContextPath()+"/patient");
                break;
        }
    }

}