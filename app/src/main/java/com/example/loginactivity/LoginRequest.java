package com.example.loginactivity;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

        final static private String URL = "http://euniceparkjob.cafe24.com/login.php";
        private Map<String, String> parameters;

        public LoginRequest(String userID, String userPassword, Response.Listener<String> listener)
        {
            super(Method.POST, URL, listener, null);
            parameters = new HashMap<>();

            Log.e("TAG", userID);
            Log.e("TAG", userPassword);

            parameters.put("userID", userID);
            parameters.put("userPassword", userPassword);
        }

        public Map<String, String> getParams()
        {
            return parameters;
        }
}
