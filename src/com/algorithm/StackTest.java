package com.algorithm;

import java.util.Stack;

/**
 * 栈的应用
 * @author hyl
 * @date 2021/10/09
 */
public class StackTest {
  /**
   * 普通的解法，用栈
   */
  private static boolean isValid(String str) {
    //为空返回false
    if (str == null || str.isEmpty()) {
      return false;
    }
    //长度为奇数，返回false
    if (str.length() % 2  !=0) {
      return false;
    }
    //等于"（"入栈，否则，出栈
    Stack<Character> stack = new Stack<>();
    for (char a : str.toCharArray()) {
      if (a=='(') {
        stack.push(a);
      }
      if (a == ')') {
        if (stack.isEmpty()) {
          return false;
        }
        stack.pop();
      }
    }
    return stack.isEmpty();
  }

  /**
   * 优化解法，只记录括号的个数，只有右括号情况怎么写？最终左括号数量不为0都为false
   */
  private static boolean isValidNew(String str) {
    if (str ==null || str.isEmpty()) {
      return false;
    }
    if (str.length() %2 ==1) {
      return false;
    }
    int leftBraceCount = 0;
    for (char a :str.toCharArray()) {
      if (a == '(') {
        leftBraceCount ++;
      }
      if (a ==')') {
        if (leftBraceCount ==0) {
          return false;
        }
        leftBraceCount --;
      }
    }
    return leftBraceCount ==0;
  }

  /**
   * 扩展，多种括号配对（）{} []
   * @param str
   */
  public static boolean multiStrMatch(String str) {
    if (str ==null || str.isEmpty()) {
      return false;
    }
    if (str.length() %2 ==1) {
      return false;
    }
    Stack<Character> stack = new Stack<>();
    for (char a: str.toCharArray()) {
      if (a== '(' || a=='{' || a== '[') {
        stack.push(a);
      } else {
        if (stack.isEmpty()) {
          return false;
        }
        if (a == ')' && stack.peek() == '(') {
          stack.pop();
        }
        if (a == '}' && stack.peek() == '{') {
          stack.pop();
        }
        if (a ==']' && stack.peek() =='[') {
          stack.pop();
        }
      }
    }
    return stack.isEmpty();
  }
  //大鱼吃小鱼，消除，循环弹栈
  public static int eatFish(int []fishSize,int[] fishDir) {
    //首先判断边界长度，不然这里就越界了
    if(fishSize.length<=1) {
      return fishSize.length;
    }
    //定义好理解的名字，见名知意
    final int left = 0;
    final int right = 1;
    int length = fishSize.length;
    Stack<Integer> fishStack = new Stack<>();
    for (int i=0;i <length;i++) {
      //当前鱼的情况
      int size = fishSize[i];
      int dir = fishDir[i];

      boolean hasEat = false;
      while (!fishStack.isEmpty() && fishDir[fishStack.peek()] == right && dir == left) {
        //只有一种情况，对向才会吃鱼
          //栈中的鱼吃掉当前鱼
           if(fishSize[fishStack.peek()] >size ) {
             hasEat = true;
             break;
           }
           //栈中的鱼被吃掉，弹栈
           fishStack.pop();
      }
      if (!hasEat) {
        fishStack.push(i);
      }
    }
    return fishStack.size();
  }

  /**
   * 查找数组中右边比我小的元素，单调递增栈： 栈中元素从小到大，遇到小的，则消除前面所有比他大的元素
   */
  public static int[] findRightSmall(int[] A) {
    int[] ans = new int[A.length];
    //栈中元素，存储的下标
    Stack<Integer> t = new Stack<>();
    //对每个元素，从右向左遍历完成消除动作
    for (int i=0;i<A.length;i++) {
      final int x = A[i];
      while (!t.empty() && A[t.peek()] >x) {
        ans[t.peek()] = i;
        t.pop();
      }
      //剩下的入栈
      t.push(i);
    }
    //栈中剩下的元素，没人消除，结果设置为-1
    while(!t.empty()) {
      ans[t.peek()] = -1;
      t.pop();
    }
    return ans;
  }

  /**
   *  数组中右边第一个比我大的元素位置，单调递减栈
   */
  public static int[] findRightBigger(int[] A) {
    //保存结果
    int[] ans = new int[A.length];
    Stack<Integer> t = new Stack<>();
    //从右到左消除比自己小的，左边最大，递减栈
    for(int i=0;i<A.length;i++) {
      final int x = A[i];
      while (!t.isEmpty() && A[t.peek()] < x) {
        ans[t.peek()] = i;
        t.pop();
      }
      //符合条件的如栈
      t.push(i);
    }
    while(!t.empty()) {
      ans[t.peek()] = -1;
      t.pop();
    }
    return ans;
  }

  /**
   * 字典序最小的K个数的子序列
   * @param nums
   * @param k
   */
  int[] findSmallSeq(int[] nums,int k) {
    int[] ans = new int[k];
    Stack<Integer> s = new Stack<>();
    //这里生成单调递增栈
    for (int i=0; i<nums.length;i++ ) {
      final int x = nums[i];
      final int left = nums.length-i;
      while (!s.isEmpty() && s.size() +left >k && s.peek()>x) {
        s.pop();
      }
      s.push(x);
    }
    while (s.size()>k) {
      s.pop();
    }
    //取出栈里面的数据，栈顶最大
    for (int i=k-1;i>=0;i--) {
      ans[i] = s.peek();
      s.pop();
    }
    return ans;
  }

  public static void main(String[] args) {
   /* String str = "()()";
    System.out.println(isValid(str));
    System.out.println(isValidNew(str));
    String multiStr = "({)}[]";
    System.out.println(multiStrMatch(multiStr));*/

    //测试
    int [] fishDir = {0,1,0,0,0};
    int [] fishSize = {4,3,2,1,5};
    System.out.println(eatFish(fishSize,fishDir));
  }
}
