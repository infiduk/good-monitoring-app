package com.example.loginactivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainJuActivity extends AppCompatActivity {
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();
        Log.d("인텐트를가져온다이말이야","가져왔다고");
        if (intent.getStringExtra("userID") != null) {
            userID = intent.getExtras().getString("userID");
        }
        else {
            Log.d("인텐트에서가져온게없다이말이야", "널임");
        }
        //Log.d("아이디가넘어왔다이말이야", intent.getExtras().getString("userID"));
        //Log.d("비밀번호가넘어왔다이말이야", intent.getExtras().getString("userPassword"));*/

        new BackgroundTask().execute();
    }

    //모든회원에 대한 정보를 가져오기 위한 쓰레드
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            //List.php은 파싱으로 가져올 웹페이지
            target = "http://euniceparkjob.cafe24.com/projectListJu.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);//URL 객체 생성

                //URL을 이용해서 웹페이지에 연결하는 부분
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                //바이트단위 입력스트림 생성 소스는 httpURLConnection
                InputStream inputStream = httpURLConnection.getInputStream();

                //웹페이지 출력물을 버퍼로 받음 버퍼로 하면 속도가 더 빨라짐
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;

                //문자열 처리를 더 빠르게 하기 위해 StringBuilder클래스를 사용함
                StringBuilder stringBuilder = new StringBuilder();

                //한줄씩 읽어서 stringBuilder에 저장함
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");//stringBuilder에 넣어줌
                }

                //사용했던 것도 다 닫아줌
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();//trim은 앞뒤의 공백을 제거함

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(MainJuActivity.this, ManagementJuActivity.class);
            intent.putExtra("userList", result);//파싱한 값을 넘겨줌
//            intent.putExtra("isAdmin", userID);
//            Log.d("파싱한값이뭐냐이말이야",result);
//            if(userID != null) {
//                Log.d("누가로그인했냐이말이야", userID);
//            } else {
//                Log.d("로그인정보가없다이말이야", "로그인안함");
//            }
            MainJuActivity.this.startActivity(intent);//ManagementRecentlyActivity로 넘어감
            finish();
        }
    }
}


