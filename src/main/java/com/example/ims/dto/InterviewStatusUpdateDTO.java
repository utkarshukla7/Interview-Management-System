package com.example.ims.dto;

public class InterviewStatusUpdateDTO {
    private Long interviewId;
    private String status;

    // Getters and setters
    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
