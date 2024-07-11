package com.example.ims.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private User candidate;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", referencedColumnName = "id")
    private User interviewer;

    private String stage;

    private LocalDate date;

    private String status;

    private String jobId;

    public Interview() {
    }

    public Interview(User candidate, User interviewer, String stage, LocalDate date, String status, String jobId) {
        this.candidate = candidate;
        this.interviewer = interviewer;
        this.stage = stage;
        this.date = date;
        this.status = status;
        this.jobId = jobId;
    }

    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
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
