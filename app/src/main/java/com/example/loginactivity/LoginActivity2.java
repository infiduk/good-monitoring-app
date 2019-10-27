package com.example.loginactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity2 extends AppCompatActivity {

    //private BackPressCloseHandler BackPressCloseHandler;

    private EditText idText, passwordText;
    private Button loginButton;
    private TextView registerButton;

    private boolean saveLoginData;
    private SharedPreferences appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.idText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (TextView) findViewById(R.id.registerButton);

        //BackPressCloseHandler = new BackPressCloseHandler(this);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        // 회원가입(register버튼) 클릭시 발생
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity2.this, RegisterActivitiy.class);
                // startActivity 로 RegisterActivitiy 화면 이동함
                LoginActivity2.this.startActivity(registerIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                if (userID.getBytes().length <= 0 || userPassword.getBytes().length <= 0) {
                    AlertDialog.Builder idpwBuilder = new AlertDialog.Builder(LoginActivity2.this);
                    idpwBuilder.setMessage("아이디와 비밀번호를 입력해주세요.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.e("로그인할때넘어오는게뭐냐이말이야", String.valueOf(success));
                                if (success) {
                                    String userID = jsonResponse.getString("userID");
                                    String userPassword = jsonResponse.getString("userPassword");
                                    Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                                    intent.putExtra("userID", userID);
                                    intent.putExtra("userPassword", userPassword);
                                    save();
                                    // Log.d("앱데이터저장이됐냐이말이야", "아이디는" + id + "비밀번호는" + pwd);
                                    LoginActivity2.this.startActivity(intent);
                                    finishAffinity();

                                /*
                                Log.e("TAG", userID);
                                Log.e("TAG", userPassword);
                                */
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity2.this);
                                    builder.setMessage("아이디와 비밀번호를 정확히 입력해주세요.")
                                            .setNegativeButton("다시시도", null)
                                            .create()
                                            .show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity2.this);
                    queue.add(loginRequest);
                }
            }
        });
    }

    // 설정값을 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", true);
        editor.putString("ID", idText.getText().toString().trim());
        editor.putString("PWD", passwordText.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }
}
