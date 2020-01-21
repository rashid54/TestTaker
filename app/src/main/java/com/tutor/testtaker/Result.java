package com.tutor.testtaker;

import java.util.ArrayList;

public class Result {
    int id;
    int user_id;
    int test_id;
    ArrayList<String> selectedAnslist;
    int score;

    public Result(ArrayList<String> selectedAnslist) {
        this.selectedAnslist = selectedAnslist;
    }

    public Result() {

    }

    public Result(int user_id, int test_id, ArrayList<String> selectedAnslist, int score) {
        this.user_id = user_id;
        this.test_id = test_id;
        this.selectedAnslist = selectedAnslist;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public ArrayList<String> getSelectedAnslist() {
        return selectedAnslist;
    }

    public void setSelectedAnslist(ArrayList<String> selectedAnslist) {
        this.selectedAnslist = selectedAnslist;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", test_id=" + test_id +
                ", selectedAnslist=" + selectedAnslist +
                ", score=" + score +
                '}';
    }
}
