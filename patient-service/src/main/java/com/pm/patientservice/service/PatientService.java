package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    public List<PatientResponseDTO> getPatients(){
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail((patientRequestDTO.getEmail()))) {
            throw new EmailAlreadyExistsException(
                    "a patient with this email already exists. "+ patientRequestDTO.getEmail()
            );
        }
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient not found with id: "+id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            //prevent email repetition occurring in update patient process.
            //check if this email is registered in the database.
            throw new EmailAlreadyExistsException(
                    "a patient with this email already exists. " + patientRequestDTO.getEmail()

            );
        }
       patient.setName(patientRequestDTO.getName().isBlank()? patient.getName() : patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress().isBlank() ? patient.getAddress() : patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail().isBlank() ? patient.getEmail() : patientRequestDTO.getEmail());

        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth().isBlank() ? patient.getDateOfBirth() : LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        Patient save = patientRepository.save(patient);
        return PatientMapper.toDto(save);
    }

    public void deletePatient(UUID uuid) {
        patientRepository.deleteById(uuid);
    }
}




