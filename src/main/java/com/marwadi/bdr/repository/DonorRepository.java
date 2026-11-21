package com.marwadi.bdr.repository;

import com.marwadi.bdr.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

    @Query("""
           SELECT d FROM Donor d
           WHERE d.availabilityStatus = 'Available'
             AND d.bloodGroup = :bloodGroup
             AND (:city = '' OR LOWER(d.city) = LOWER(:city))
           ORDER BY d.city
           """)
    List<Donor> radarSearch(@Param("bloodGroup") String bloodGroup, @Param("city") String city);
}