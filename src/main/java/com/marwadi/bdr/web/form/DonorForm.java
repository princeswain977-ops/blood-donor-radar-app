package com.marwadi.bdr.web.form;

import com.marwadi.bdr.entity.Donor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class DonorForm {

    public static final List<String> BLOOD_GROUPS = List.of("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
    public static final List<String> STATUSES = List.of("Available", "Unavailable");

    private Integer donorId;

    @NotBlank(message = "Full name is required.")
    @Size(min = 3, max = 100, message = "Full name must be 3 to 100 characters.")
    private String fullName;

    @NotBlank(message = "Blood group is required.")
    private String bloodGroup;

    @NotBlank(message = "Contact number is required.")
    @Pattern(regexp = "[0-9]{10}", message = "Contact number must be exactly 10 digits.")
    private String contactNumber;

    @Email(message = "Enter a valid email address.")
    @Size(max = 100, message = "Email must be at most 100 characters.")
    private String email;

    @NotBlank(message = "City is required.")
    @Size(max = 50, message = "City must be at most 50 characters.")
    private String city;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "Last donation date cannot be in the future.")
    private LocalDate lastDonationDate;

    @NotBlank(message = "Availability status is required.")
    private String availabilityStatus;

    private Integer bankId;

    public DonorForm() {
    }

    // Copies a saved Donor into the form (used for the Edit page)
    public static DonorForm from(Donor d) {
        DonorForm f = new DonorForm();
        f.setDonorId(d.getDonorId());
        f.setFullName(d.getFullName());
        f.setBloodGroup(d.getBloodGroup());
        f.setContactNumber(d.getContactNumber());
        f.setEmail(d.getEmail());
        f.setCity(d.getCity());
        f.setLastDonationDate(d.getLastDonationDate());
        f.setAvailabilityStatus(d.getAvailabilityStatus());
        f.setBankId(d.getBank() == null ? null : d.getBank().getBankId());
        return f;
    }

    public Integer getDonorId() { return donorId; }
    public void setDonorId(Integer donorId) { this.donorId = donorId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public LocalDate getLastDonationDate() { return lastDonationDate; }
    public void setLastDonationDate(LocalDate lastDonationDate) { this.lastDonationDate = lastDonationDate; }

    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public Integer getBankId() { return bankId; }
    public void setBankId(Integer bankId) { this.bankId = bankId; }
}