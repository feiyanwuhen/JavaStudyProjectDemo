package com.study.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

/*  public static void main(String[] args) {
*//*    Pattern p =  Pattern.compile("[a-z|A-Z]+");
    Matcher matcher = p.matcher("abc pdf");
    if(matcher.find()) {
      if (matcher.groupCount() > 1) {
        System.out.println(matcher.group(0));
      }
      System.out.println(matcher.group(0));
    }
    Lock lock = new ReentrantLock();*//*
*//*    int a =3 ,b =4;
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println(a);
    System.out.println(b);*//*

  }*/

   public static Pattern jobNamePattern = Pattern.compile("^[\\w\\u4e00-\\u9fa5-——\\(\\)（）【】\\[\\]\\+#\\/\\s]+$");
    // public static Pattern jobNamePattern = Pattern.compile("^[\u4e00-\u9fa5]*$");
    private RegularTest(){

    }
    public static boolean isValidJobName(String jobName) {
      Matcher matcher = jobNamePattern.matcher(jobName);
      return matcher.matches();
    }

    public static void main(String[] args) {
      String jobName = "dfd  dfdsfds";
      System.out.println(isValidJobName(jobName));
    }
  }

