package com.pm.patientservice.repository;

import com.pm.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    /**
     *
     * @param email patient email
     * @param uuid patient uuid
     * @return if there is a patient with the @param email but do not have the same @param uuid, return true, Otherwise return false.
     */
    boolean existsByEmailAndIdNot(String email, UUID uuid);



    boolean existsByEmail(String email);

    UUID id(UUID id);
}
