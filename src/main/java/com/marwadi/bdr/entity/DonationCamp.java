package com.marwadi.bdr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "donation_camp")
@Getter @Setter @NoArgsConstructor
public class DonationCamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "camp_id")
    private Integer campId;

    @Column(name = "camp_name", nullable = false, length = 100)
    private String campName;

    @Column(length = 150)
    private String location;

    @Column(name = "camp_date")
    private LocalDate campDate;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private BloodBank bank;
}