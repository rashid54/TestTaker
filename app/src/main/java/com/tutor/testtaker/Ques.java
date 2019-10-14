package com.tutor.testtaker;

import java.util.Arrays;

public class Ques {
    private String question;
    private String ans[];
    private String ans_correct;
    private String ans_given;
    private boolean correct;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getAns_given() {
        return ans_given;
    }

    public void setAns_given(String ans_given) {
        this.ans_given = ans_given;
    }

    public Ques(String question, String[] ans, String ans_correct) {
        this.question = question;
        this.ans = ans;
        this.ans_correct = ans_correct;
    }

    public Ques(String question,String ans0,String ans1,String ans2,String ans3, String ans_correct) {
        this.question = question;
        this.ans= new String[4];
        this.ans[0]=ans0;
        this.ans[1]=ans1;
        this.ans[2]=ans2;
        this.ans[3]=ans3;
        this.ans_correct = ans_correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAns() {
        return ans;
    }

    public void setAns(String[] ans) {
        this.ans = ans;
    }

    public String getAns_correct() {
        return ans_correct;
    }

    public void setAns_correct(String ans_correct) {
        this.ans_correct = ans_correct;
    }

    @Override
    public String toString() {
        return "Ques{" +
                "question='" + question + '\'' +
                ", ans=" + Arrays.toString(ans) +
                ", ans_correct='" + ans_correct + '\'' +
                '}';
    }
}