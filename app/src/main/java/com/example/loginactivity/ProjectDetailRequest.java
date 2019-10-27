package com.example.loginactivity;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 상세정보 DB에서 가져옴
public class ProjectDetailRequest extends StringRequest {

    final static private String URL = "http://euniceparkjob.cafe24.com/projectdetail.php";
    private Map<String, String> parameters;

    public ProjectDetailRequest(String projectName, Response.Listener<String> listener)
    {
        super(Request.Method.POST, URL, listener, null);
        Log.e("마이에스큐엘이실행이다이말이야","mysqlstart");
        parameters = new HashMap<>();
        parameters.put("name", projectName);
        Log.e("선택한프로젝트이름이출력되냐이말이야",String.valueOf(parameters));
    }

    public Map<String, String> getParams()
    {
        return parameters;
    }
}

