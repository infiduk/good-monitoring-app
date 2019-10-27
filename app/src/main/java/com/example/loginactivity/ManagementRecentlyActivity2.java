package com.example.loginactivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ManagementRecentlyActivity2 extends AppCompatActivity {
    private ListView listView;
    private ProjectListAdapter adapter;
    private List<Project> userList;
    private DrawerLayout mDrawerLayout;
    private ImageView userImage;
    private TextView userIdInfo, userMessage;
    private NavigationView navigationView;
    private ScrollView scrollView;

    private boolean saveLoginData;

    private SharedPreferences appData;

    private String id, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_recently);
        Intent intent = getIntent();

        scrollView = findViewById(R.id.scroll);
        listView = (ListView)findViewById(R.id.listView);
        userList = new ArrayList<Project>();
        Log.e("리스트가생성됐냐이말이야", "리스트생성");

        navigationView = (NavigationView) findViewById(R.id.nav);
        View headerView = navigationView.getHeaderView(0);

        userImage = headerView.findViewById(R.id.getImageUser);
        userIdInfo = headerView.findViewById(R.id.getTextId);
        userMessage = headerView.findViewById(R.id.getUserIng);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();
        Log.d("앱데이터들어갔냐이말이야","아이디는" + id + "비밀번호는" + pwd);

        if (id != null) {
            if (id.equals("admin")) {
                Log.d("앱데이터에정보가있냐이말이야", "앱데이터에는아이디가" + id + "비밀번호가" + pwd);
                userImage.setImageResource(R.drawable.admin);
                userIdInfo.setText("관리자");
                userMessage.setText("현재 접속중 입니다.");
            } else {
                Log.d("앱데이터에도정보가있냐이말이야", "앱데이터에는아이디가" + id + "비밀번호가" + pwd);
                userImage.setImageResource(R.drawable.person);
                userIdInfo.setText(id + " 님");
                userMessage.setText("현재 접속중 입니다.");
            }
        } else {
            Log.d("로그인정보가없다이말이야", "처음앱실행");
            userIdInfo.setText("앱 방문을 환영합니다!");
            userMessage.setText("현재 로그아웃 상태 입니다.");
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //어댑터 초기화부분 userList와 어댑터를 연결해준다.
        adapter = new ProjectListAdapter(getApplicationContext(), userList);
        listView.setAdapter(adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));

            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("result");

            int count = 0;

            String projectName, projectDate2;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                projectName = object.getString("name");//여기서 ID가 대문자임을 유의
                projectDate2 = object.getString("projectDate2");

                //값들을 User클래스에 묶어줍니다
                Project user = new Project(projectName, "~ " + projectDate2);
                userList.add(user);//리스트뷰에 값을 추가해줍니다
                count++;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        // ListViev 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 item의 1번 값을 가져옴
                final String projectName = userList.get(position).projectName;

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");
                            Log.e("리스트가잘넘어오냐이말이야", String.valueOf(success));
                            if(success)
                            {
                                String detailname       = jsonResponse.getString("detailname");
                                String detailneedman    = jsonResponse.getString("detailneedman");
                                String detaildate1       = jsonResponse.getString("detaildate1");
                                String detaildate2      = jsonResponse.getString("detaildate2");
                                String detailneeds      = jsonResponse.getString("detailneeds");
                                String detailpass       = jsonResponse.getString("detailpass");
                                String detailtip        = jsonResponse.getString("detailtip");
                                String detailevent      = jsonResponse.getString("detailevent");
                                String detaildate3      = jsonResponse.getString("detaildate3");
                                String detaildate4      = jsonResponse.getString("detaildate4");
                                String detailcont       = jsonResponse.getString("detailcont");
                                String detailcate = jsonResponse.getString("detailcate");
                                String detailimage = jsonResponse.getString("detailimage");

                                Intent intent = new Intent(ManagementRecentlyActivity2.this, UserProjectDetail.class);
                                intent.putExtra("detailname", detailname);
                                intent.putExtra("detailneedman", detailneedman);
                                intent.putExtra("detaildate1", detaildate1);
                                intent.putExtra("detaildate2", detaildate2);
                                intent.putExtra("detailneeds", detailneeds);
                                intent.putExtra("detailpass", detailpass);
                                intent.putExtra("detailtip", detailtip);
                                intent.putExtra("detailevent", detailevent);
                                intent.putExtra("detaildate3", detaildate3);
                                intent.putExtra("detaildate4", detaildate4);
                                intent.putExtra("detailcont", detailcont);
                                intent.putExtra("detailimage", detailimage);
                                intent.putExtra("detailcate", detailcate);
                                ManagementRecentlyActivity2.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManagementRecentlyActivity2.this);
                                builder.setMessage("상세보기에 실패했습니다.").setNegativeButton("확인", null).create().show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                ProjectDetailRequest projectDetailRequest = new ProjectDetailRequest(projectName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ManagementRecentlyActivity2.this);
                queue.add(projectDetailRequest);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id1 = menuItem.getItemId();
                switch (id1) {
                    case R.id.gotoMain:
                        Intent intent1 = new Intent(ManagementRecentlyActivity2.this, MainActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent1);
                        finish();
                        break;

                    case R.id.recently:
                        Intent intent2 = new Intent(ManagementRecentlyActivity2.this, MainRecentlyActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent2);
                        finish();
                        break;

                    case R.id.allCategory:
                        Intent intent3 = new Intent(ManagementRecentlyActivity2.this, MainAllCategoryActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent3);
                        finish();
                        break;

                    case R.id.studentCategory:
                        Intent intent4 = new Intent(ManagementRecentlyActivity2.this, MainDaeActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent4);
                        finish();
                        break;

                    case R.id.womanCategory:
                        Intent intent5 = new Intent(ManagementRecentlyActivity2.this, MainJuActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent5);
                        finish();
                        break;

                    case R.id.normalCategory:
                        Intent intent6 = new Intent(ManagementRecentlyActivity2.this, MainIllActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent6);
                        finish();
                        break;

                    case R.id.globalCategory:
                        Intent intent7 = new Intent(ManagementRecentlyActivity2.this, MainGloActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent7);
                        finish();
                        break;

                    case R.id.grandCategory:
                        Intent intent8 = new Intent(ManagementRecentlyActivity2.this, MainSiniActivity.class);
                        ManagementRecentlyActivity2.this.startActivity(intent8);
                        finish();
                        break;

                    case R.id.useInfoResume:
                        if(id.equals("admin")) {
                            Intent intent9 = new Intent(ManagementRecentlyActivity2.this, MainRecentlyActivity3.class);
                            ManagementRecentlyActivity2.this.startActivity(intent9);
                            finish();
                        }
                        break;
                }
                return true;
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

    private void delete() {
        SharedPreferences.Editor editor = appData.edit();
        editor.remove("appData");
        editor.clear();
        editor.commit();
    }
}
