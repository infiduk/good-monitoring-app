package com.example.loginactivity;

public class Project {

        String projectName;
        String projectDate;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectDate() {
            return projectDate;
        }

        public void setProjectDate(String projectDate) {
            this.projectDate = projectDate;
        }

        // 리스트 배열
        public Project(String projectName, String projectDate) {
            this.projectName = projectName;
            this.projectDate = projectDate;
        }
}
