package com.example.flashcardproject;

import java.io.Serializable;

public class Flashcard implements Serializable {
    private int id;
    private String question;
    private String answer;
    private String wronganswer1;
    private String wronganswer2;

    public Flashcard(String question,String answer,String wronganswer1,String wronganswer2){
        this.question=question;
        this.answer=answer;
        this.wronganswer1=wronganswer1;
        this.wronganswer2=wronganswer2;
    }

    public Flashcard(String question,String answer,String wronganswer1,String wronganswer2,int id){
        this.question=question;
        this.answer=answer;
        this.wronganswer1=wronganswer1;
        this.wronganswer2=wronganswer2;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getWronganswer1() {
        return wronganswer1;
    }

    public String getWronganswer2() {
        return wronganswer2;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setWronganswer1(String wronganswer1) {
        this.wronganswer1 = wronganswer1;
    }

    public void setWronganswer2(String wronganswer2) {
        this.wronganswer2 = wronganswer2;
    }
}
