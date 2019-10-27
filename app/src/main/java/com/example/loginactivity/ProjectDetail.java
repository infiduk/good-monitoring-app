package com.example.loginactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

// 상세정보 출력
// AppCompatActivity 는 안드로이드 3.0 이하의 버전에서도 동작을 지원하기 위해 사용됨, 굳이 안써도 되는데 호환성 떄문에 사용함

public class ProjectDetail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        final EditText detailname   = (EditText)findViewById(R.id.showname);
        final EditText detailneedman= (EditText)findViewById(R.id.showneedman);
        final EditText detaildate   = (EditText)findViewById(R.id.showdate);
        final EditText detailneeds  = (EditText)findViewById(R.id.showneeds);
        final EditText detailpass   = (EditText)findViewById(R.id.showpass);
        final EditText detailtip    = (EditText)findViewById(R.id.showtip);
        final EditText detailevent  = (EditText)findViewById(R.id.showevent);
        final EditText detaildate2  = (EditText)findViewById(R.id.showdate2);
        final EditText detailcont  = (EditText)findViewById(R.id.showcont);

        Intent intent = getIntent();
        String name    = intent.getStringExtra("detailname");
        String needman = intent.getStringExtra("detailneedman");
        String date    = intent.getStringExtra("detaildate");
        String needs   = intent.getStringExtra("detailneeds");
        String pass    = intent.getStringExtra("detailpass");
        String tip     = intent.getStringExtra("detailtip");
        String event   = intent.getStringExtra("detailevent");
        String date2   = intent.getStringExtra("detaildate2");
        String cont    = intent.getStringExtra("detailcont");

        detailname.setText(name);
        detailneedman.setText(needman);
        detaildate.setText(date);
        detailneeds.setText(needs);
        detailpass.setText(pass);
        detailtip.setText(tip);
        detailevent.setText(event);
        detaildate2.setText(date2);
        detailcont.setText(cont);
    }
}
