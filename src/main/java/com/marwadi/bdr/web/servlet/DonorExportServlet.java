package com.marwadi.bdr.web.servlet;

import com.marwadi.bdr.entity.Donor;
import com.marwadi.bdr.service.DonorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DonorExportServlet extends HttpServlet {

    private final DonorService donorService;

    // ServletConfig creates this servlet and passes in the DonorService
    public DonorExportServlet(DonorService donorService) {
        this.donorService = donorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/csv");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Disposition", "attachment; filename=\"donors.csv\"");

        List<Donor> donors = donorService.findAll();
        PrintWriter out = resp.getWriter();

        out.println("ID,Full Name,Blood Group,Contact,Email,City,Last Donation,Availability,Blood Bank");
        for (Donor d : donors) {
            String lastDate = d.getLastDonationDate() == null ? "" : d.getLastDonationDate().toString();
            String bankName = d.getBank() == null ? "" : d.getBank().getBankName();

            out.println(String.join(",",
                    csv(String.valueOf(d.getDonorId())),
                    csv(d.getFullName()),
                    csv(d.getBloodGroup()),
                    csv(d.getContactNumber()),
                    csv(d.getEmail()),
                    csv(d.getCity()),
                    csv(lastDate),
                    csv(d.getAvailabilityStatus()),
                    csv(bankName)));
        }
        out.flush();
    }

    // Wraps a value in quotes so commas inside it don't break the CSV
    private String csv(String value) {
        if (value == null) {
            return "\"\"";
        }
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}