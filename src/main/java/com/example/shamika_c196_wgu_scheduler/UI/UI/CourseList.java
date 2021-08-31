package com.example.shamika_c196_wgu_scheduler.UI.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shamika_c196_wgu_scheduler.R;
import com.example.shamika_c196_wgu_scheduler.UI.Database.Repository;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.CourseEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CourseList extends AppCompatActivity {


    private Repository repository;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;
    TermEntity currentTerm;
    public static int numCourses;
    RecyclerView recyclerView;
    private int id;
    List<TermEntity> allTerms;
    private CourseEntity currentCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        id=getIntent().getIntExtra("termID", -1);
        repository = new Repository(getApplication());
        allTerms=repository.getAllTerms();
        for(TermEntity t:allTerms){
            if(t.getTermID()==id)currentTerm=t;
        }
        editTitle=findViewById(R.id.editTextTermTitle);
        editStart=findViewById(R.id.editTextTermStartDate);
        editEnd=findViewById(R.id.editTextTermEndDate);
        if(currentTerm!=null){
            editTitle.setText(currentTerm.getTermTitle());
            editStart.setText(currentTerm.getStartDate());
            editEnd.setText(currentTerm.getEndDate());
        }
        RecyclerView recyclerView=findViewById(R.id.courserecyclerview);
        final CourseAdapter courseAdapter=new CourseAdapter(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CourseEntity> filteredCourses=new ArrayList<>();
        for(CourseEntity c: repository.getAllCourses()){
            if(c.getTermID()==id)filteredCourses.add(c);
        }
        numCourses=filteredCourses.size();
        courseAdapter.setCourses(filteredCourses);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;


            case R.id.delete:
                if (numCourses == 0) {
                    repository.delete(currentTerm);
                    Toast.makeText(getApplicationContext(), "Term deleted successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CourseList.this, TermList.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot delete a Term with associated courses", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.refresh:
                refreshCourseList();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term, menu);
        return true;
    }

    public void saveCourse(View view){

    }

    public void enterAssessments(View view) {
        Intent intent=new Intent(CourseList.this, AssessmentList.class);
        if(currentCourses!=null) intent.putExtra("courseID",currentCourses.getTermID());
        intent.putExtra("termID", id);
        startActivity(intent);
    }



    private void refreshCourseList(){
        recyclerView = findViewById(R.id.courserecyclerview);
        final CourseAdapter adapter=new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<CourseEntity> filteredCourses = new ArrayList<>();
        List<CourseEntity> allCourses =repository.getAllCourses();
        for (CourseEntity c : allCourses) {
            if (c.getTermID() == id)
                filteredCourses.add(c);
        }
        numCourses = filteredCourses.size();
        adapter.setCourses(filteredCourses);
    }

    public void saveTerm(View view) {

        if(id==-1){
            id=allTerms.get(allTerms.size()-1).getTermID();
            TermEntity t = new TermEntity(++id,editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString());
            repository.insert(t);
        }
        else {
            TermEntity t= new TermEntity(id,editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString());
            repository.update(t);
        }
        Intent intent= new Intent(CourseList.this,TermList.class);
        startActivity(intent);
    }



}
