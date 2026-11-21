package com.marwadi.bdr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "donation_record")
@Getter @Setter @NoArgsConstructor
public class DonationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Integer donationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "camp_id")
    private DonationCamp camp;

    @Column(name = "donation_date")
    private LocalDate donationDate;

    @Column(name = "units_donated")
    private Integer unitsDonated;
}