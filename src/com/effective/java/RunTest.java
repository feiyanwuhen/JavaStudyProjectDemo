package com.effective.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RunTest {

  public static void main(String[] args) throws Exception{
      int tests = 0;
      int passed = 0;
      Sample sample = new Sample();
      Class testClass = sample.getClass();
      //Class<?> testClass = Class.forName("com.effective.java.Sample");
      for (Method m : testClass.getDeclaredMethods()) {
        if (m.isAnnotationPresent(Test.class)) {
          tests ++;
          try {
            m.invoke(sample);
            //m.invoke(sample);
            passed ++;
          } catch (InvocationTargetException wrappedExc) {
            Throwable exc = wrappedExc.getCause();
            System.out.println(m + " failed: " + exc);
          } catch (Exception exc) {
            System.out.println("Invalid @Test: " + m);
          }
        }
      }
  }

}
