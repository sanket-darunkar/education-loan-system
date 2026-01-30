package com.els.educationloansystem.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.els.educationloansystem.entity.StudentProfile;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByStudent_Id(Long studentId);
}
