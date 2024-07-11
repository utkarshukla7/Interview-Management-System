package com.example.ims.service;

import com.example.ims.dto.InterviewDateUpdateDTO;
import com.example.ims.dto.InterviewRequestDTO;
import com.example.ims.dto.InterviewStatusUpdateDTO;
import com.example.ims.entity.Interview;

public interface InterviewService {
    Interview createInterview(InterviewRequestDTO interview);
    Interview findByInterviewId(Long interviewId);
    Interview findByCandidateId(Long candidateId);
    Interview findByInterviewerId(Long interviewerId);
    Interview updateStatus(InterviewStatusUpdateDTO statusUpdate);
    Interview updateDate(InterviewDateUpdateDTO dateUpdate);
    void deleteInterview(Long interviewId);
}
