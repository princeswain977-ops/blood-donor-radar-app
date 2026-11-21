package com.marwadi.bdr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "blood_bank")
@Getter @Setter @NoArgsConstructor
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Integer bankId;

    @Column(name = "bank_name", nullable = false, length = 100)
    private String bankName;

    @Column(length = 200)
    private String address;

    @Column(length = 50)
    private String city;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;
}