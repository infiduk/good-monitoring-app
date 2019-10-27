package com.example.loginactivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://euniceparkjob.cafe24.com/register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userName, String userPassword, String userEmail, String userTell, String userAge, String userAdd, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userPassword", userPassword);
        parameters.put("userEmail", userEmail);
        parameters.put("userTell", userTell);
        parameters.put("userAge", userAge);
        parameters.put("userAdd", userAdd);

    }
    public Map<String, String> getParams()
    {
        return parameters;
    }
}
