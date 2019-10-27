package com.example.loginactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ProjectResumeActivity extends AppCompatActivity {
    private BackPressCloseHandler BackPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_resume);

        BackPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override public void onBackPressed() {
        //super.onBackPressed();
        BackPressCloseHandler.onBackPressed();
    }
}

