package com.example.ims.controller;

import com.example.ims.dto.InterviewRequestDTO;
import com.example.ims.dto.InterviewStatusUpdateDTO;
import com.example.ims.dto.InterviewDateUpdateDTO;
import com.example.ims.entity.Interview;
import com.example.ims.exception.UserNotFoundException;
import com.example.ims.service.InterviewServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewServiceImpl interviewService;

    @PostMapping("/createinterview")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createInterview(@RequestBody InterviewRequestDTO interviewRequest) {
        try {
            Interview interview = interviewService.createInterview(interviewRequest);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Interview: " + e.getMessage());
        }
    }

    @GetMapping("/get_interview_by_interviewid")
    public ResponseEntity<?> interviewByInterviewId(@RequestParam Long interviewId) {
        try {
            Interview interview = interviewService.findByInterviewId(interviewId);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get_interview_by_candidateid")
    public ResponseEntity<?> interviewByCandidateId(@RequestParam Long candidateId) {
        try {
            Interview interview = interviewService.findByCandidateId(candidateId);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get_interview_by_interviewerid")
    public ResponseEntity<?> interviewByInterviewerId(@RequestParam Long interviewerId) {
        try {
            Interview interview = interviewService.findByInterviewerId(interviewerId);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update_interview_status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INTERVIEWER')")
    public ResponseEntity<?> updateInterviewStatus(@RequestBody InterviewStatusUpdateDTO statusUpdateDTO) {
        try {
            Interview interview = interviewService.updateStatus(statusUpdateDTO);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/update_interview_date")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INTERVIEWER')")
    public ResponseEntity<?> updateInterviewDate(@RequestBody InterviewDateUpdateDTO dateUpdateDTO) {
        try {
            Interview interview = interviewService.updateDate(dateUpdateDTO);
            return ResponseEntity.ok(interview);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete_interview/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteInterview(@PathVariable Long id) {
        try {
            interviewService.deleteInterview(id);
            return ResponseEntity.ok("Interview deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
