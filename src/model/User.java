package model;

import major.Major;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Job job;
    private Major major;
    private List<Subject> subjects = new ArrayList();
    private String userId;
    private String password;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public boolean isStudent(){
        return this.job.equals(Job.STUDENT);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isMatchPassword(String password){
        return this.password.equals(password);
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Major getMajor() {
        return major;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public User(Job job, Major major, String userId, String password) {
        this.job = job;
        this.major = major;
        this.userId = userId;
        this.password = password;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public boolean isAlreadyEnterSubject(Subject subject){
        return this.subjects.contains(subject);
    }
}
