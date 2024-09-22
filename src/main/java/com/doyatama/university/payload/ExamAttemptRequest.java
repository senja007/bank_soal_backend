package com.doyatama.university.payload;

import com.doyatama.university.model.Exam;
import com.doyatama.university.model.StudentAnswer;
import com.doyatama.university.model.User;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class ExamAttemptRequest {
    private List<String> studentAnswers;
    private String exam_id;
    private String user_id;
    private String student_id;
    private Integer duration;
    public ExamAttemptRequest() {
    }

    public ExamAttemptRequest(List<String> studentAnswers, String exam_id, String user_id, String student_id, Integer duration) {
        this.studentAnswers = studentAnswers;
        this.exam_id = exam_id;
        this.user_id = user_id;
        this.student_id = student_id;
        this.duration = duration;
    }

    public List<String> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void set(String fieldName, String value) {
        switch (fieldName) {
            case "studentAnswers":
                this.studentAnswers = Collections.singletonList(value);
                break;
            case "exam_id":
                this.exam_id = value;
                break;
            case "user_id":
                this.user_id = value;
                break;
            case "student_id":
                this.student_id = value;
                break;
            case "duration":
                this.duration = Integer.parseInt(value);
                break;
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
    }
}
