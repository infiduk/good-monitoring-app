package com.example.loginactivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;


public class UserProjectModify extends AppCompatActivity {
    private File tempFile;
    private SharedPreferences appData;
    private String imagePath;
    private String imageName, rbText, mainName;
    private boolean saveLoginData;
    private String id, pwd;
    private TextView setTextView;

    private URL serverUri;
    private URLConnection conn;

    private Bitmap bitmap;

    private String upLoadServerUri = "http://euniceparkjob.cafe24.com/UploadToServer.php";
    private int serverResponseCode = 0;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_user_project_modify);

        final RadioGroup editrg = findViewById(R.id.radiogroup);
        final RadioButton editrb1 = findViewById(R.id.radio1);
        final RadioButton editrb2 = findViewById(R.id.radio2);
        final RadioButton editrb3 = findViewById(R.id.radio3);
        final RadioButton editrb4 = findViewById(R.id.radio4);
        final RadioButton editrb5 = findViewById(R.id.radio5);
        final EditText editname = (EditText) findViewById(R.id.showname);
        final EditText editneedman = (EditText) findViewById(R.id.showneedman);
        final EditText editdate1 = findViewById(R.id.showdate1);
        final EditText editdate2 = findViewById(R.id.showdate2);
        final EditText editneeds = (EditText) findViewById(R.id.showneeds);
        final EditText editpass = (EditText) findViewById(R.id.showpass);
        final EditText edittip = (EditText) findViewById(R.id.showtip);
        final EditText editevent = (EditText) findViewById(R.id.showevent);
        final EditText editdate231 = (EditText) findViewById(R.id.showdate231);
        final EditText editdate232 = findViewById(R.id.showdate232);
        final EditText editcont = (EditText) findViewById(R.id.showcont);
        final Button getfile = findViewById(R.id.getfile);
        setTextView = findViewById(R.id.getfilename);

        Intent intent = getIntent();

        ImageButton registerButton = (ImageButton) findViewById(R.id.check);

        // 받아온 값을 셋팅
        String name    = intent.getStringExtra("detailname");
        mainName = intent.getStringExtra("detailname");
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

        Log.d("카테가뭐가넘어왔냐이말이야", cate + "다");

        if(cate.equals("대학생")) { editrb1.setChecked(true); rbText = "대학생"; }
        else if(cate.equals("일반")) { editrb2.setChecked(true); rbText = "일반"; }
        else if(cate.equals("주부")) { editrb3.setChecked(true); rbText = "주부"; }
        else if(cate.equals("글로벌")) { editrb4.setChecked(true); rbText = "글로벌"; }
        else if(cate.equals("시니어")) { editrb5.setChecked(true); rbText = "시니어"; }
        editname.setText(name);
        editneedman.setText(needman);
        editdate1.setText(date1);
        editdate2.setText(date2);
        editneeds.setText(needs);
        editpass.setText(pass);
        edittip.setText(tip);
        editevent.setText(event);
        editdate231.setText(date3);
        editdate232.setText(date4);
        editcont.setText(cont);

        editrb1.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                rbText = "대학생";
                Log.d("어떤게체크됐냐이말이야", "응" + rbText);
            }
        });
        editrb2.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                rbText = "일반";
                Log.d("어떤게체크됐냐이말이야", "응" + rbText);
            }
        });
        editrb3.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                rbText = "주부";
                Log.d("어떤게체크됐냐이말이야", "응" + rbText);
            }
        });
        editrb4.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                rbText = "글로벌";
                Log.d("어떤게체크됐냐이말이야", "응" + rbText);
            }
        });
        editrb5.setOnClickListener(new RadioButton.OnClickListener() {
            public void onClick(View v) {
                rbText = "시니어";
                Log.d("어떤게체크됐냐이말이야", "응" + rbText);
            }
        });

        editdate1.setInputType(0);
        editdate2.setInputType(0);
        editpass.setInputType(0);
        editdate231.setInputType(0);
        editdate232.setInputType(0);

        editdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();

                Log.e("이말이야1", cal.get(Calendar.YEAR)+"");
                Log.e("이말이야2", cal.get(Calendar.MONTH)+1+"");
                Log.e("이말이야3", cal.get(Calendar.DATE)+"");
                Log.e("이말이야4", cal.get(Calendar.HOUR_OF_DAY)+"");
                Log.e("이말이야5", cal.get(Calendar.MINUTE)+"");

                DatePickerDialog dialog = new DatePickerDialog(UserProjectModify.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%04d%02d%02d", year, month+1, date);

                        Log.d("첫번쨰날짜선택했다이말이야", msg);
                        editdate1.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });

        editdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();

                Log.e("이말이야1", cal.get(Calendar.YEAR)+"");
                Log.e("이말이야2", cal.get(Calendar.MONTH)+1+"");
                Log.e("이말이야3", cal.get(Calendar.DATE)+"");
                Log.e("이말이야4", cal.get(Calendar.HOUR_OF_DAY)+"");
                Log.e("이말이야5", cal.get(Calendar.MINUTE)+"");

                DatePickerDialog dialog = new DatePickerDialog(UserProjectModify.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%04d%02d%02d", year, month+1, date);

                        Log.d("두번째날짜선택했다이말이야", msg);
                        editdate2.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });

        editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();

                Log.e("이말이야1", cal.get(Calendar.YEAR)+"");
                Log.e("이말이야2", cal.get(Calendar.MONTH)+1+"");
                Log.e("이말이야3", cal.get(Calendar.DATE)+"");
                Log.e("이말이야4", cal.get(Calendar.HOUR_OF_DAY)+"");
                Log.e("이말이야5", cal.get(Calendar.MINUTE)+"");

                DatePickerDialog dialog = new DatePickerDialog(UserProjectModify.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%04d%02d%02d", year, month+1, date);

                        Log.d("세번쨰날짜선택했다이말이야", msg);
                        editpass.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });

        editdate231.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();

                Log.e("이말이야1", cal.get(Calendar.YEAR)+"");
                Log.e("이말이야2", cal.get(Calendar.MONTH)+1+"");
                Log.e("이말이야3", cal.get(Calendar.DATE)+"");
                Log.e("이말이야4", cal.get(Calendar.HOUR_OF_DAY)+"");
                Log.e("이말이야5", cal.get(Calendar.MINUTE)+"");

                DatePickerDialog dialog = new DatePickerDialog(UserProjectModify.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%04d%02d%02d", year, month+1, date);

                        Log.d("네번째날짜선택했다이말이야", msg);
                        editdate231.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });

        editdate232.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();

                Log.e("이말이야1", cal.get(Calendar.YEAR)+"");
                Log.e("이말이야2", cal.get(Calendar.MONTH)+1+"");
                Log.e("이말이야3", cal.get(Calendar.DATE)+"");
                Log.e("이말이야4", cal.get(Calendar.HOUR_OF_DAY)+"");
                Log.e("이말이야5", cal.get(Calendar.MINUTE)+"");

                DatePickerDialog dialog = new DatePickerDialog(UserProjectModify.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.format("%04d%02d%02d", year, month+1, date);

                        Log.d("다섯번쨰날짜선택했다이말이야", msg);
                        editdate232.setText(msg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });


        final String mtext = imageName;

        Log.d("dd","dd");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname = editname.getText().toString();
                String mneedman = editneedman.getText().toString();
                String mdate1 = editdate1.getText().toString();
                String mdate2 = editdate2.getText().toString();
                String mneeds = editneeds.getText().toString();
                String mpass = editpass.getText().toString();
                String mtip = edittip.getText().toString();
                String mevent = editevent.getText().toString();
                String mdate231 = editdate231.getText().toString();
                String mdate232 = editdate232.getText().toString();
                String mcont = editcont.getText().toString();
                String mtext = imageName;
                String mcate = rbText;
                String name = mainName;
                Log.d("디비들어가기전카테다이말이야", "이응" + mcate);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("버튼클릭시쓰레드런이다이말이야","그래");
                            }
                        });
                        uploadFile(imagePath);
                    }
                }).start();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserProjectModify.this);
                                // builder.setMessage("공고 등록에 성공했습니다.").setPositiveButton("확인", null).create().show();

                                Intent intent = new Intent(UserProjectModify.this, MainRecentlyActivity3.class);
                                UserProjectModify.this.startActivity(intent);
                                Toast.makeText(getApplicationContext(), "공고 수정에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserProjectModify.this);
                                builder.setMessage("공고 수정에 실패했습니다.").setNegativeButton("확인", null).create().show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                UserProjectModifyRequest userProjectModifyRequest = new UserProjectModifyRequest(mname, mneedman, mdate1, mdate2, mneeds, mpass, mtip, mevent, mdate231, mdate232, mcont, mtext, mcate, name, responseListener);
                RequestQueue queue = Volley.newRequestQueue(UserProjectModify.this);
                queue.add(userProjectModifyRequest);
            }
        });

        getfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1);
                Log.d("파일탐색기가열렸냐이말이야", "열림");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.d("유알아이다이말이야", "uri" + uri);
            Cursor cursor = null;
            try {
                String[] proj = { MediaStore.Images.Media.DATA };

                assert uri != null;
                cursor = getContentResolver().query(uri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));
                imagePath = tempFile.getAbsolutePath();
                Log.d("절대경로다이말이야",imagePath);
                imageName = tempFile.getName();
                Log.d("파일명이다이말이야",imageName);
            }
            finally {
                if(cursor != null) {
                    cursor.close();
                }
            }
        }
        setTextView.setText(imageName);
    }

    public int uploadFile(String sourceFileUri) {
        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;

        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;

        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {
            Log.e("파일이없다이말이야", "Source File not exist : " + imagePath + " 이름은 " + imageName);

            runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("런온쓰레드안의런이다이말이야","그래");
                }
            });
            return 0;
        }
        else
        {
            try {
                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);
                dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);

                buffer = new byte[bufferSize];
                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Log.d("업로드끝이다이말이야","그렇다니까");
                        }
                    });
                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();
            } catch (MalformedURLException ex) {
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("업로드실패다이말이야","그렇구나");
                    }
                });

                Log.e("어떤에러로실패했냐이말이야", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("이건무슨에러지","흠");
                    }
                });

                Log.e("서버익셉션이다이말이야", "Exception : "
                        + e.getMessage(), e);
            }
            return serverResponseCode;
        } // End else block
    }

}
