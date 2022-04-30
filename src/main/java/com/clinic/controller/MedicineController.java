package com.clinic.controller;

import com.clinic.dao.MedicineDao;
import com.clinic.entity.Medicine;
import com.clinic.mapper.IMapper;
import com.clinic.mapper.MedicineMapper;
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

@WebServlet("/medicine/*")
public class MedicineController extends HttpServlet {

    private MedicineDao medicineDao;

    @Override
    public void init() throws ServletException {
        IMapper<Medicine> medicineMapper = new MedicineMapper();
        JdbcTemplate<Medicine> jdbcTemplate = new JdbcTemplate<>(medicineMapper);
        this.medicineDao = new MedicineDao(jdbcTemplate);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            List<Medicine> medicines = this.medicineDao.findAll();
            req.setAttribute("medicines", medicines);
            req.getRequestDispatcher("/view/medicine/list-medicine.jsp").forward(req, resp);
            return;
        }

        switch (pathInfo) {
            case "/delete":
                String code = req.getParameter("code");
                Optional<Medicine> medicineOptional = this.medicineDao.findByCode(code);
                if (!medicineOptional.isPresent()) {
                    throw new RuntimeException(code + " dont exist");
                }
                this.medicineDao.delete(code);
                resp.sendRedirect(req.getContextPath()+"/medicine");
                break;
            case "/form":
                req.setAttribute("medicine", new Medicine());
                req.setAttribute("countries", SystemUtils.getCountries());
                req.getRequestDispatcher("/view/medicine/form-medicine.jsp").forward(req, resp);
                break;
            case "/update":
                String medicineCode = req.getParameter("code");
                Optional<Medicine> medicineOpt = this.medicineDao.findByCode(medicineCode);
                req.setAttribute("medicine", medicineOpt.orElseThrow(() -> new RuntimeException("not found medicine: "+medicineCode)));
                req.setAttribute("countries", SystemUtils.getCountries());
                req.getRequestDispatcher("/view/medicine/form-medicine.jsp").forward(req, resp);
                break;
            case "/search":
                String keyword = req.getParameter("keyword");
                req.setAttribute("medicines", this.medicineDao.doSearch(keyword));
                req.getRequestDispatcher("/view/medicine/list-medicine.jsp").forward(req, resp);
                break;
            default:
                List<Medicine> medicines = this.medicineDao.findAll();
                req.setAttribute("medicines", medicines);
                req.getRequestDispatcher("/view/medicine/list-medicine.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Medicine medicine = Convertor.requestParamToObject(req, Medicine.class);
        String pathInfo = req.getPathInfo();
        if (Objects.isNull(pathInfo)) {
            return;
        }

        switch (pathInfo) {
            case "/form":
                Optional<Medicine> medicineOpt = this.medicineDao.findByCode(medicine.getCode());
                if (medicineOpt.isPresent()) {
                    req.setAttribute("error", medicineOpt.get().getCode()+ " exist");
                    req.setAttribute("countries", SystemUtils.getCountries());
                    req.getRequestDispatcher("/view/medicine/form-medicine.jsp").forward(req, resp);
                    return;
                }
                String message = Validator.validate(medicine);
                if (Objects.nonNull(message)) {
                    req.setAttribute("error", message);
                    req.setAttribute("countries", SystemUtils.getCountries());
                    req.getRequestDispatcher("/view/medicine/form-medicine.jsp").forward(req, resp);
                    return;
                }
                this.medicineDao.insert(medicine);
                resp.sendRedirect(req.getContextPath()+"/medicine");
                break;
            case "/update":
                String messageUpdate = Validator.validate(medicine);
                if (Objects.nonNull(messageUpdate)) {
                    req.setAttribute("error", messageUpdate);
                    req.setAttribute("countries", SystemUtils.getCountries());
                    req.getRequestDispatcher("/view/medicine/form-medicine.jsp").forward(req, resp);
                    return;
                }
                this.medicineDao.update(medicine.getCode(), medicine);
                resp.sendRedirect(req.getContextPath()+"/medicine");
                break;
        }

    }
}