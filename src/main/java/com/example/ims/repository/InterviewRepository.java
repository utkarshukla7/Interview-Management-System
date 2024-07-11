package com.example.ims.repository;

import com.example.ims.entity.Interview;
import com.example.ims.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long> {
    Optional<Interview> findByCandidate(User candidate);
    Optional<Interview> findByInterviewer(User interviewer);
}
