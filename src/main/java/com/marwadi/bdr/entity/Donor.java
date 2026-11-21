package com.marwadi.bdr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "donor")
@Getter @Setter @NoArgsConstructor
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donor_id")
    private Integer donorId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "blood_group", nullable = false, length = 5)
    private String bloodGroup;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String city;

    @Column(name = "last_donation_date")
    private LocalDate lastDonationDate;

    @Column(name = "availability_status", length = 20)
    private String availabilityStatus;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BloodBank bank;
}