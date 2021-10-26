package com.study.test;

public class ModelAnswer implements Cloneable{

  private int id;

  private String content;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return this.id + " : " + this.content;
  }

  public static void main(String[] args) throws CloneNotSupportedException {
    ModelAnswer answer = new ModelAnswer();
    answer.setId(1);
    answer.setContent("testAnswer");
    ModelAnswer answerNew = (ModelAnswer) answer.clone();
    System.out.println(answerNew.toString());
  }
}
