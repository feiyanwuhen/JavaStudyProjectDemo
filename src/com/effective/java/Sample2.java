package com.effective.java;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {

  @ExceptionTest(ArithmeticException.class)
  public static void m1() {
    int i = 0;
    i = i / i;
  }

  @ExceptionTest(ArithmeticException.class)
  public static void m2() { // Should fail (wrong exception)
    int[]a = new int[0];
    int i = a[1];
  }

  @ExceptionTest(ArithmeticException.class)
  public static void m3() {} // Should fail (no exception)

  @ExceptionTest({ArrayIndexOutOfBoundsException.class,NullPointerException.class})
  //@ExceptionTest({IndexOutOfBoundsException.class,NullPointerException.class})
  public static void doublyBad() {
    List<String> list = new ArrayList<>();
    // IndexOutOfBoundsException or NullPointerException
    list.addAll(5,null);
  }

  @ExceptionTestTwo(IndexOutOfBoundsException.class)
  @ExceptionTestTwo(NullPointerException.class)
  public static void doublyBadTwo() {
    List<String> list = new ArrayList<>();
    // IndexOutOfBoundsException or NullPointerException
    list.addAll(5,null);
  }
}
