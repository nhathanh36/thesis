package com.english_for_kid.thanhnha.englishforkid.model;

/**
 * Created by Thanh Nha on 3/7/2015.
 */
public class Question_Look {
    int image;
    String correct_answer;
    String answerB;
    String answerC;
    String answerD;
    int group;

    public Question_Look() {

    }
    public Question_Look(int image, String correct_answer, String answerB, String answerC, String answerD, int group) {
        this.image = image;
        this.correct_answer = correct_answer;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.group = group;
    }

    public int getImage() {
        return image;
    }

    public String getCorrectAnswer() {
        return correct_answer;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setCorrectAnswer(String answerA) {
        this.correct_answer = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
