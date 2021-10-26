package com.effective.java;

import java.lang.reflect.Method;

public class RunRepeatAnnotationTest {
  public static void main(String[] args) throws Exception{
    int tests = 0;
    int passed = 0;
    Class<?> testClass = Class.forName("com.effective.java.Sample2");
    for (Method m : testClass.getDeclaredMethods()) {
      //一定要两个判断都加上，使用重复注解时 m.isAnnotationPresent(ExceptionTestTwo.class） false
      //使用非重复注解时 m.isAnnotationPresent(ExceptionTestContainer.class) false
      if (m.isAnnotationPresent(ExceptionTestTwo.class) ||
          m.isAnnotationPresent(ExceptionTestContainer.class))
      {
        tests ++;
        try {
          m.invoke(null);
          System.out.printf("Test %s failed: no exception%n",m);
        } catch (Throwable wrappedEx) {
          Throwable exc = wrappedEx.getCause();
          int oldPassed = passed;
          ExceptionTestTwo[] excTypes =
              m.getAnnotationsByType(ExceptionTestTwo.class);
          //判定异常类型是否跟注解中相同
          for (ExceptionTestTwo excType : excTypes) {
            if (excType.value().isInstance(exc)) {
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
