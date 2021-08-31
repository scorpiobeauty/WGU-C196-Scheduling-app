package com.example.shamika_c196_wgu_scheduler.UI.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shamika_c196_wgu_scheduler.R;
import com.example.shamika_c196_wgu_scheduler.UI.Database.Repository;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.AssessmentEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.CourseEntity;
import com.example.shamika_c196_wgu_scheduler.UI.Entities.TermEntity;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repository = new Repository(getApplication());
        TermEntity termEntity = new TermEntity(1,"Term 1", "08/22/21","08/01/22" );
        repository.insert(termEntity);
        CourseEntity courseEntity = new CourseEntity(1, "C196", "08/22/21","08/30/22","in-progress","Carolyn","555-555-5555","c@wgu.edu", "test notes", 1);
        repository.insert(courseEntity);
        AssessmentEntity assessmentEntity = new AssessmentEntity(1,"task project", "08/22/21", "08/30/21","Performance",1);
        repository.insert(assessmentEntity);
    }


    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);

    }
}