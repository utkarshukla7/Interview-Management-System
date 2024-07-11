package com.example.ims.dto;

import java.time.LocalDate;

public class InterviewDateUpdateDTO {
    private Long interviewId;
    private LocalDate date;

    // Getters and setters
    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
