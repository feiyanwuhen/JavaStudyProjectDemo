package com.effective.java.override;

public class MathOperate extends AbstractOperate {


  @Override
  public int add(int a, int b) {
    return a + b;
  }

  //可以不写Override
  @Override
  public int multi(int a, int b) {
    return  a * b;
  }


  @Override
  public int minus(int a, int b) {
    return a + b;
  }

  public static void main(String[] args) {
    int a = 10 ;
    int b = 20 ;
    MathOperate mathOperate = new MathOperate();

    System.out.println(mathOperate.add(a,b));
    System.out.println(mathOperate.multi(a,b));
    System.out.println(mathOperate.minus(a,b));
  }
}
