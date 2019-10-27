package com.example.loginactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UserProjectDetail extends AppCompatActivity {
    private URL serverUri;
    private URLConnection conn;

    private String imagePath;
    private Bitmap bitmap;

    private boolean saveLoginData;

    private SharedPreferences appData;

    private String id, pwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project_detail);

        final TextView addTitle = findViewById(R.id.addtitle);
        final TextView mainText   = (TextView)findViewById(R.id.mainText);
        final TextView personText2 = (TextView)findViewById(R.id.personText2);
        final TextView whenText2   = (TextView)findViewById(R.id.whenText2);
        final TextView whenText3 = findViewById(R.id.whenText3);
        final TextView needsText2  = (TextView)findViewById(R.id.needsText2);
        final TextView celeText2   = (TextView)findViewById(R.id.celeText2);
        final TextView howText2    = (TextView)findViewById(R.id.howText2);
        final TextView presentText2  = (TextView)findViewById(R.id.presentText2);
        final TextView playText2  = (TextView)findViewById(R.id.playText2);
        final TextView playText3 = findViewById(R.id.playText3);
        final TextView etcText2  = (TextView)findViewById(R.id.etcText2);
        final Button button2 = findViewById(R.id.button);
        final ImageView projectImage = findViewById(R.id.projectImage);
        final TextView cateText = findViewById(R.id.cateText);

        Intent intent = getIntent();
        String name    = intent.getStringExtra("detailname");
        String needman = intent.getStringExtra("detailneedman");
        String date1    = intent.getStringExtra("detaildate1");
        String date2 = intent.getStringExtra("detaildate2");
        String needs   = intent.getStringExtra("detailneeds");
        String pass    = intent.getStringExtra("detailpass");
        String tip     = intent.getStringExtra("detailtip");
        String event   = intent.getStringExtra("detailevent");
        String date3   = intent.getStringExtra("detaildate3");
        String date4 = intent.getStringExtra("detaildate4");
        String cont    = intent.getStringExtra("detailcont");
        String image   = intent.getStringExtra("detailimage");
        String cate = intent.getStringExtra("detailcate");

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        // serverUri = Uri.parse("http://euniceparkjob.cafe24.com/uploads/" + image);
        imagePath = "http://euniceparkjob.cafe24.com/uploads/" + image;
        Log.d("이미지패쓰다이말이야", imagePath);

        // 이미지 Null 처리 ..
        if(image.equals("null")) {
            Log.d("이미지패쓰가널이다이말이야",image + "제발널이라고해줘");
            projectImage.setImageResource(R.drawable.noimage);
        }
        else {
            Log.d("이미지패쓰가널이아니다이말이야",image + "널이아님");
            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(imagePath);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();

            try {
                mThread.join();
                projectImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cateText.setText("분류 : " + cate);
        addTitle.setText(name);
        mainText.setText(name);
        personText2.setText(needman);
        whenText2.setText(date1);
        whenText3.setText(date2);
        needsText2.setText(needs);
        celeText2.setText(pass);
        howText2.setText(tip);
        presentText2.setText(event);
        playText2.setText(date3);
        playText3.setText(date4);
        etcText2.setText(cont);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserProjectDetail.this);
                    builder.setMessage("준비중입니다.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserProjectDetail.this);
                    builder.setMessage("회원가입 후 이용 가능합니다.")
                            .setNegativeButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // 확인시 처리 로직
                                    Intent intent = new Intent(UserProjectDetail.this, LoginActivity2.class);
                                    UserProjectDetail.this.startActivity(intent);
                                }})
                            .create()
                            .show();
                }
            }
        });
    }

    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", null);
        pwd = appData.getString("PWD", null);
    }
}
