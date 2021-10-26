package com.study.test;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputTest {

  public static void main(String[] args) throws IOException {
/*    Scanner scanner = new Scanner(System.in);
    System.out.println("what your name:");
    String name = scanner.nextLine();
    System.out.println("your age");
    int age = scanner.nextInt();
    System.out.println("Hello: " + name + " age: " + age );*/
    //只能用于命令行启动
    /*Console cons = System.console();
    String userName = cons.readLine("your name:");
    char[] password = cons.readPassword("your password");
    System.out.println("Hello: " + userName
         + " password: " + password.toString() );*/
    String dir = System.getProperty("user.dir");
    System.out.println(dir);
    PrintWriter printWriter = new PrintWriter("myfile1.txt", "UTF-8");
    Scanner in = new Scanner(Paths.get(dir + "/src/com/study/test/myFile.txt"),"UTF-8");
    while (in.hasNext()) {
      String text = in.nextLine();
      System.out.println(text);
      printWriter.write(text);
    }


  }
}
