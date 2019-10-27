package com.example.loginactivity;


import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserProjectModifyRequest extends StringRequest {

    final static private String URL = "http://euniceparkjob.cafe24.com/UserprojectModipyAdd.php";
    private Map<String, String> parameters;


    public UserProjectModifyRequest(String mname, String mneedman, String mdate1, String mdate2, String mneeds, String mpass, String mtip, String mevent, String mdate231, String mdate232, String mcont, String mtext, String mcate, String name, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("mname", mname);
        parameters.put("mneedman", mneedman);
        parameters.put("mdate1", mdate1);
        parameters.put("mdate2", mdate2);
        parameters.put("mneeds", mneeds);
        parameters.put("mpass", mpass);
        parameters.put("mtip", mtip);
        parameters.put("mevent", mevent);
        parameters.put("mdate231", mdate231);
        parameters.put("mdate232", mdate232);
        parameters.put("mcont", mcont);
        parameters.put("mtext", mtext);
        parameters.put("mcate", mcate);
        parameters.put("name", name);
    }

    public Map<String, String> getParams()
    {
        Log.e("Map", String.valueOf(parameters));
        return parameters;
    }
}
