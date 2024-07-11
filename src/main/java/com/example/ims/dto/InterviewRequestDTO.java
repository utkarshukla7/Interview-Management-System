package com.example.ims.dto;

import java.time.LocalDate;

public class InterviewRequestDTO {
    private Long candidateId;
    private Long interviewerId;
    private String stage;
    private LocalDate date;
    private String status;
    private String jobId;

    public InterviewRequestDTO() {
    }

    public InterviewRequestDTO(Long candidateId, Long interviewerId, String stage, LocalDate date, String status, String jobId) {
        this.candidateId = candidateId;
        this.interviewerId = interviewerId;
        this.stage = stage;
        this.date = date;
        this.status = status;
        this.jobId = jobId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Long interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}