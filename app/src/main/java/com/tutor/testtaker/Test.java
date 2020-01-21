package com.tutor.testtaker;

import java.util.ArrayList;

public class Test {
    private String testname;
    private long duration;
    private ArrayList<Ques> queslist;
    private int totalques;
    private int id;
    private int user_profile;

    public Test() {
    }

    public Test(String testname, long duration, ArrayList<Ques> queslist, int totalques, int id, int user_profile) {
        this.testname = testname;
        this.duration = duration;
        this.queslist = queslist;
        this.totalques = totalques;
        this.id = id;
        this.user_profile = user_profile;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public ArrayList<Ques> getQueslist() {
        return queslist;
    }

    public void setQueslist(ArrayList<Ques> queslist) {
        this.queslist = queslist;
    }

    public int getTotalques() {
        return totalques;
    }

    public void setTotalques(int totalques) {
        this.totalques = totalques;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(int user_profile) {
        this.user_profile = user_profile;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testname='" + testname + '\'' +
                ", duration=" + duration +
                ", queslist=" + queslist +
                ", totalques=" + totalques +
                ", id=" + id +
                ", user_profile=" + user_profile +
                '}';
    }
}
