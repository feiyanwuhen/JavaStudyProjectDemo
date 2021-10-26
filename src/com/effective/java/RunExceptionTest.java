package com.effective.java;

import java.lang.reflect.Method;

public class RunExceptionTest {

  public static void main(String[] args) throws Exception{
    int tests = 0;
    int passed = 0;
    Class<?> testClass = Class.forName("com.effective.java.Sample2");
    for (Method m : testClass.getDeclaredMethods()) {
      if (m.isAnnotationPresent(ExceptionTest.class)) {
        tests ++;
        try {
          m.invoke(null);
          System.out.printf("Test %s failed: no exception%n",m);
        } catch (Throwable wrappedEx) {
          Throwable exc = wrappedEx.getCause();
          int oldPassed = passed;
          Class<? extends Exception>[] excTypes =
              m.getAnnotation(ExceptionTest.class).value();
          //判定异常类型是否跟注解中相同
          for (Class<? extends Exception> excType : excTypes) {
            if (excType.isInstance(exc)) {
              passed++;
              break;
            }
          }
          //没有一个异常符合，测试失败
          if (passed == oldPassed) {
            System.out.printf("Test %s failed: %s %n",m,exc);
          }
        }
      }
    }
    System.out.printf("Passed: %d, Failed: %d%n",
        passed, tests - passed);
  }

}
