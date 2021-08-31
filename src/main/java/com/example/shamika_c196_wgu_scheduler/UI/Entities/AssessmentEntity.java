package com.example.shamika_c196_wgu_scheduler.UI.Entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table")
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String type;
    private String title;
    private String startDate;
    private String endDate;
    private int courseID;

    public AssessmentEntity(int assessmentID, String type, String title, String startDate, String endDate, int courseID) {
        this.assessmentID = assessmentID;
        this.type = type;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseID = courseID;
    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
