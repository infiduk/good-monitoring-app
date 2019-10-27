package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivitiy extends AppCompatActivity {
    // private BackPressCloseHandler BackPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //String userName, String userPassword, String userEmail, String userTell, String userAge, String userAdd,

       final EditText nameText = (EditText) findViewById(R.id.nameText);
       final EditText passwordText = (EditText) findViewById(R.id.pwText);
       final EditText emailText = (EditText) findViewById(R.id.emailText);
       final EditText tellText = (EditText) findViewById(R.id.telText);
       final EditText ageText = (EditText) findViewById(R.id.ageText);
       final EditText addText = (EditText) findViewById(R.id.addText);

        Button registerButton = (Button) findViewById(R.id.registerButton);

        // BackPressCloseHandler = new BackPressCloseHandler(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userEmail = emailText.getText().toString();
                String userTell = tellText.getText().toString();
                String userAge = ageText.getText().toString();
                String userAdd = addText.getText().toString();

                if (userName.getBytes().length <= 0 || userPassword.getBytes().length <= 0 || userEmail.getBytes().length <= 0 || userTell.getBytes().length <= 0 ||
                        userAge.getBytes().length <= 0 || userAdd.getBytes().length <= 0) {
                    AlertDialog.Builder regiBuilder = new AlertDialog.Builder(RegisterActivitiy.this);
                    regiBuilder.setMessage("회원가입에 필요한 모든 정보를 입력해주세요.")
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
                                if (success) {
                                    Toast.makeText(getApplicationContext(), "회원 가입이 정상적으로 처리되었습니다.", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(RegisterActivitiy.this, LoginActivity.class);
                                    RegisterActivitiy.this.startActivity(intent);
                                    finish();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivitiy.this);
                                    builder.setMessage("다시 시도해주세요.").setNegativeButton("확인", null).create().show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(userName, userPassword, userEmail, userTell, userAge, userAdd, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivitiy.this);
                    queue.add(registerRequest);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // BackPressCloseHandler.onBackPressed();

    }
}
