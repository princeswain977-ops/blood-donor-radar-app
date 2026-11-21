package com.marwadi.bdr.service;

import com.marwadi.bdr.entity.BloodBank;
import com.marwadi.bdr.entity.Donor;
import com.marwadi.bdr.repository.BloodBankRepository;
import com.marwadi.bdr.repository.DonorRepository;
import com.marwadi.bdr.web.form.DonorForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;
    private final BloodBankRepository bloodBankRepository;

    public DonorService(DonorRepository donorRepository, BloodBankRepository bloodBankRepository) {
        this.donorRepository = donorRepository;
        this.bloodBankRepository = bloodBankRepository;
    }

    @Transactional(readOnly = true)
    public List<Donor> findAll() {
        return donorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Donor findById(Integer donorId) {
        return donorRepository.findById(donorId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Donor> search(String bloodGroup, String city) {
        return donorRepository.radarSearch(bloodGroup, city);
    }

    @Transactional
    public Donor save(DonorForm form) {
        Donor donor;
        if (form.getDonorId() != null) {
            donor = donorRepository.findById(form.getDonorId())
                    .orElseThrow(() -> new IllegalArgumentException("Donor not found: " + form.getDonorId()));
        } else {
            donor = new Donor();
        }

        donor.setFullName(form.getFullName());
        donor.setBloodGroup(form.getBloodGroup());
        donor.setContactNumber(form.getContactNumber());
        donor.setEmail(form.getEmail());
        donor.setCity(form.getCity());
        donor.setLastDonationDate(form.getLastDonationDate());
        donor.setAvailabilityStatus(form.getAvailabilityStatus());
        donor.setBank(findBank(form.getBankId()));

        return donorRepository.save(donor);
    }

    @Transactional
    public void delete(Integer donorId) {
        donorRepository.deleteById(donorId);
    }

    private BloodBank findBank(Integer bankId) {
        if (bankId == null) {
            return null;
        }
        return bloodBankRepository.findById(bankId).orElse(null);
    }
}