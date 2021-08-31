package com.example.shamika_c196_wgu_scheduler.UI.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import java.util.ArrayList;
import java.util.List;


public class TermList extends AppCompatActivity {

    private Repository repository;
    TermEntity currentTerms;
    private RecyclerView recyclerView;
    private int numTerms;
    private int id;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        repository=new Repository(getApplication());
        List<TermEntity> allTerms =repository.getAllTerms();

        RecyclerView recyclerView=findViewById(R.id.termrecyclerview);
        final TermAdapter termAdapter=new TermAdapter(this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;


            case R.id.refresh:
                refreshTermList();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview, menu);
        return true;
    }

    public void enterCourses(View view) {
        Intent intent = new Intent(TermList.this, CourseList.class);
        if(currentTerms!=null) intent.putExtra("termID",currentTerms.getTermID());
        startActivity(intent);
    }

    public void startDate(View view) {
    }

    private void refreshTermList(){
        recyclerView = findViewById(R.id.termrecyclerview);
        final TermAdapter adapter=new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<TermEntity> filteredTerms = new ArrayList<>();
        List<TermEntity> allTerms =repository.getAllTerms();
        /*
        for (TermEntity t : allTerms) {
            if (t.getTermID() == id)
                filteredTerms.add(t);
        }

         */
        numTerms = filteredTerms.size();
        adapter.setTerms(allTerms);
    }



}

