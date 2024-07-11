package com.example.ims.service;

import com.example.ims.dto.InterviewDateUpdateDTO;
import com.example.ims.dto.InterviewRequestDTO;
import com.example.ims.dto.InterviewStatusUpdateDTO;
import com.example.ims.entity.Interview;
import com.example.ims.entity.User;
import com.example.ims.exception.UserNotFoundException;
import com.example.ims.repository.InterviewRepository;
import com.example.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
@Service
public class InterviewServiceImpl implements InterviewService{

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Interview createInterview(InterviewRequestDTO interview){
        User candidate = userRepository.findById(interview.getCandidateId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));

        User interviewer = userRepository.findById(interview.getInterviewerId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid interviewer ID"));

        LocalDate interviewDate = interview.getDate();
        if (interviewDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        if (interviewDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }

        Interview createdInterview = new Interview(candidate, interviewer, interview.getStage(),
                interviewDate, interview.getStatus(), interview.getJobId());

        return interviewRepository.save(createdInterview);
    }

    @Override
    public Interview findByInterviewId(Long interviewId){
        Interview existingInterview = interviewRepository.findById(interviewId).orElseThrow(() -> new UserNotFoundException("Interview not found with id: " + interviewId));
        return existingInterview;
    }
    @Override
    public Interview findByCandidateId(Long candidateId) {

        User candidate = userRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));

        Interview existingInterview = interviewRepository.findByCandidate(candidate)
                .orElseThrow(() -> new IllegalArgumentException("Interview not found for candidate with ID: " + candidateId));

        return existingInterview;
    }

    @Override
    public Interview findByInterviewerId(Long interviewerId){
        User interviewer = userRepository.findById(interviewerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate ID"));

        Interview existingInterview = interviewRepository.findByInterviewer(interviewer)
                .orElseThrow(() -> new IllegalArgumentException("Interview not found for interview with ID: " + interviewerId));

        return existingInterview;
    }

    @Override
    public Interview updateStatus(InterviewStatusUpdateDTO statusUpdate){
        Interview interview = interviewRepository.findById(statusUpdate.getInterviewId())
                .orElseThrow(() -> new IllegalArgumentException("Interview not found with ID: " + statusUpdate.getInterviewId()));

        interview.setStatus(statusUpdate.getStatus());
        return interviewRepository.save(interview);
    }

    @Override
    public Interview updateDate(InterviewDateUpdateDTO dateUpdate){
        Interview interview = interviewRepository.findById(dateUpdate.getInterviewId())
                .orElseThrow(() -> new IllegalArgumentException("Interview not found with ID: " + dateUpdate.getInterviewId()));
        LocalDate interviewDate = dateUpdate.getDate();
        if (interviewDate == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        if (interviewDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }
        interview.setDate(dateUpdate.getDate());
        return interviewRepository.save(interview);
    }

    @Override
    public void deleteInterview(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid interview ID"));
        interviewRepository.delete(interview);
    }
}
