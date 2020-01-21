package com.tutor.testtaker;

import java.util.ArrayList;

public class Test {
    private String testName;
    private long testTime;
    private ArrayList<Ques> queslist;
    private ArrayList<String> correctAnslist;
    private int testId;
//
//    public Test(String testName, int testTime, ArrayList<Ques> queslist) {
//        this.testName = testName;
//        this.testTime = testTime;
//        this.queslist = queslist;
//        genCorrectAnslist();
//        genId();
//    }
//
//    public void genCorrectAnslist(){
//        correctAnslist= new ArrayList<>();
//        for(Ques q:queslist){
//            correctAnslist.add(q.getAns_correct());
//        }
//    }
//
//    public void genId(){
//        // Todo: The code to find the id value of the test
//    }
//
//    public void setTestName(String testName) {
//        this.testName = testName;
//    }
//
//    public void setTestTime(int testTime) {
//        this.testTime = testTime;
//    }
//
//    public void setQueslist(ArrayList<Ques> queslist) {
//        this.queslist = queslist;
//    }
//
//    public String getTestName() {
//        return testName;
//    }
//
//    public long getTestTime() {
//        return testTime;
//    }
//
//    public ArrayList<Ques> getQueslist() {
//        return queslist;
//    }
//
//    public static long totalCorrectAns(ArrayList<Ques> queslist,ArrayList<String>selectedAnslist)
//    {
//        int sum=0;
//        for(int i=0;i<queslist.size();i++)
//        {
//            if(queslist.get(i).getAns()==selectedAnslist.get(i)){
//                sum++;
//            }
//        }
//        return sum;
//    }
//
//    public long totalCorrectAns()
//    {
//        int sum=0;
//        for(Ques q:queslist)
//        {
//            if(q.getAns_correct()==q.getAns_given()){
//                sum++;
//                q.setCorrect(true);
//            }
//            else {
//                q.setCorrect(false);
//            }
//        }
//        return sum;
//    }
}
