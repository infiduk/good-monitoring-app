package com.example.loginactivity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by kch on 2018. 2. 17..
 */

public class ProjectListAdapter extends BaseAdapter {
    private Context context;
    private List<Project> projectList;

    public ProjectListAdapter(Context context, List<Project> projectList){
        this.context = context;
        this.projectList = projectList;
    }

    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return projectList.size();
    }

    //특정한 유저를 반환하는 메소드
    @Override
    public Object getItem(int i) {
        return projectList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.project, null);

        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView projectName = (TextView)v.findViewById(R.id.projectName);
        TextView projectDate = (TextView)v.findViewById(R.id.projectDate);

        projectName.setText(projectList.get(i).getProjectName());
        projectDate.setText(projectList.get(i).getProjectDate());

        //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임
        v.setTag(projectList.get(i).getProjectName());

        //만든뷰를 반환함
        return v;
    }
}
