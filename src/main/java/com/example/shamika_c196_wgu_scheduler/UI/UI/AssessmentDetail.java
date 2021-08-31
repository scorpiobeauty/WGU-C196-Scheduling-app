package com.example.shamika_c196_wgu_scheduler.UI.UI;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.shamika_c196_wgu_scheduler.UI.Entities.AssessmentEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentDetail extends AppCompatActivity {

    private Repository repository;
    private int id;
    private int courseID;
    List<AssessmentEntity> allAssessments;
    AssessmentEntity currentAssessment;
    EditText editType;
    EditText editTitle;
    EditText editStart;
    EditText editEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        id=getIntent().getIntExtra("assessmentID", -1);
        courseID=getIntent().getIntExtra("courseID", -1);
        repository = new Repository(getApplication());
        allAssessments=repository.getAllAssessments();
        for(AssessmentEntity a:allAssessments){
            if(a.getAssessmentID()==id)currentAssessment=a;
        }
        editType=findViewById(R.id.editTextAssessmentType);
        editTitle=findViewById(R.id.editTextAssessmentTitle);
        editStart=findViewById(R.id.editTextAssessmentStartDate);
        editEnd=findViewById(R.id.editTextAssessmentEndDate);
        if(currentAssessment!=null){
            editType.setText(currentAssessment.getType());
            editTitle.setText(currentAssessment.getTitle());
            editStart.setText(currentAssessment.getStartDate());
            editEnd.setText(currentAssessment.getEndDate());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                repository.delete(currentAssessment);
                Toast.makeText(getApplicationContext(), "Assessment deleted successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AssessmentDetail.this, AssessmentList.class);
                startActivity(intent);

            case R.id.notifystart:
                String assessmentDateStart= editStart.getText().toString();
                String assessmentTitle = editTitle.getText().toString();
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                Date date = null;
                try {
                    date=simpleDateFormat.parse(assessmentDateStart);
                } catch (ParseException e){
                    e.printStackTrace();
                }
                Long trig = date.getTime();
                Intent intent1 = new Intent(AssessmentDetail.this , MyReceiver.class);
                intent1.putExtra("key", assessmentTitle + " STARTS TODAY " + assessmentDateStart);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert,intent1,0 );
                AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trig,pendingIntent);
                return true;

            case R.id.notifyend:
                String assessmentDateEnd= editEnd.getText().toString();
                assessmentTitle = editTitle.getText().toString();
                dateFormat = "MM/dd/yy";
                simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                date = null;
                try {
                    date=simpleDateFormat.parse(assessmentDateEnd);
                } catch (ParseException e){
                    e.printStackTrace();
                }
                trig = date.getTime();
                intent1 = new Intent(AssessmentDetail.this, MyReceiver.class);
                intent1.putExtra("key", assessmentTitle +" ENDS TODAY "+ assessmentDateEnd);
                pendingIntent = PendingIntent.getBroadcast(AssessmentDetail.this, ++MainActivity.numAlert, intent1, 0);
                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trig,pendingIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_assessments, menu);
        return true;
    }

    public void saveAssessment(View view) {
        if(id==-1){
            id=allAssessments.get(allAssessments.size()-1).getAssessmentID();
            AssessmentEntity newAssessment = new AssessmentEntity(++id,editType.getText().toString(), editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString(), courseID);
            repository.insert(newAssessment);
        }
        else {
            AssessmentEntity updateAssessment = new AssessmentEntity(id, editType.getText().toString(),editTitle.getText().toString(),editStart.getText().toString(),editEnd.getText().toString(), courseID);
            repository.update(updateAssessment);
        }
        Intent intent= new Intent(AssessmentDetail.this,AssessmentList.class);
        startActivity(intent);
    }

}