package com.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hyl
 * @date 2021/10/15
 */
public class JumpTest {
  static int  jumpLength(int[] arr, int brick,int landers) {
    if (arr ==null || arr.length ==0) {
      return -1;
    }
    int sum = 0;
    Queue<Integer> queue = new PriorityQueue<>((v1,v2)-> v2-v1);
    for (int i=0; i< arr.length-1;i++) {
      int step = arr[i]-arr[i+1];
      if (step > 0) {
        continue;
      }
      sum = sum +Math.abs(step);
      if (sum <=brick) {
        queue.offer(step);
      } else {
        if (landers>0) {
          landers--;
          sum = sum - queue.peek();
          queue.poll();
        } else {
          return i;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] arr = {3,1,6,20,10,20};
    System.out.println(jumpLength(arr,5,1));
  }
}
