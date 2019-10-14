package com.tutor.testtaker;

import java.util.ArrayList;

public class Test {
    private String testName;
    private long testTime;
    private ArrayList<Ques> queslist;
    private ArrayList<String> correctAnslist;
    private int testId;

    public Test(String testName, int testTime, ArrayList<Ques> queslist) {
        this.testName = testName;
        this.testTime = testTime;
        this.queslist = queslist;
        genCorrectAnslist();
        genId();
    }

    public void genCorrectAnslist(){
        correctAnslist= new ArrayList<>();
        for(Ques q:queslist){
            correctAnslist.add(q.getAns_correct());
        }
    }

    public void genId(){
        // Todo: The code to find the id value of the test
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public void setQueslist(ArrayList<Ques> queslist) {
        this.queslist = queslist;
    }

    public String getTestName() {
        return testName;
    }

    public long getTestTime() {
        return testTime;
    }

    public ArrayList<Ques> getQueslist() {
        return queslist;
    }
}
