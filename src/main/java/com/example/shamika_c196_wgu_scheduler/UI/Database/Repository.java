package com.example.shamika_c196_wgu_scheduler.UI.Database;

import android.app.Application;

import com.example.shamika_c196_wgu_scheduler.UI.DAO.AssessmentDAO;
import com.example.shamika_c196_wgu_scheduler.UI.DAO.CourseDAO;
import com.example.shamika_c196_wgu_scheduler.UI.DAO.TermDAO;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.AssessmentEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.CourseEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<TermEntity>mAllTerms;
    private List<CourseEntity>mAllCourses;
    private List<AssessmentEntity>mAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabase db = TermDatabase.getDatabase(application);
        mTermDAO = db.termDAO();

        CourseDatabase cdb = CourseDatabase.getDatabase(application);
            mCourseDAO = cdb.courseDAO();


        AssessmentDatabase adb = AssessmentDatabase.getDatabase(application);
            mAssessmentDAO = adb.assessmentDAO();


    }

    public List<TermEntity> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms= mTermDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.insert(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.update(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete(TermEntity termEntity){
        databaseExecutor.execute(()->{
            mTermDAO.delete(termEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public List<CourseEntity> getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses= mCourseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.insert(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.update(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete(CourseEntity courseEntity){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(courseEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public List<AssessmentEntity> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments= mAssessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update(AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void delete(AssessmentEntity assessmentEntity){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessmentEntity);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
