package com.effective.java;
/**
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 */
public class Sample {

  @Test
  public static void m1() {} //Test should pass

  public static void m2() {}

  @Test
  public static void m3() {
    throw new RuntimeException("Boom");
  }

  public static void m4() {}

  @Test
  public void m5() {} //INVALID USE : nonstatic method



  public  static void m6() {}

  @Test
  public static void m7() {
    throw new RuntimeException("Crash");
  }

  public static void m8() {}

  @Test
  public static void m9(int a) {} //INVALID USE : multi parameter
}
