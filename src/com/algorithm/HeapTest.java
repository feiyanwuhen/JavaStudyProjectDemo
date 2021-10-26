package com.algorithm;

/**
 * @author hyl
 * @date 2021/10/15
 */
public class HeapTest {
  private int[]a = null;
  private int n = 0;
  //下沉,和子结点比
  public void sink(int i) {
    int j = 0;
    int t = a[i];
    while ((j = (i << 1) +1) <n) {
      if(j< n-1 && a[j] <a[j+1]) {
        j++;
      }
      if(a[j] >t) {
        a[i] = a[j];
        i = j;
      } else {
        break;
      }
    }
    a[i] = t;
  }
  //上浮，和父节点比
  public void swim(int i) {
    int t = a[i];
    int par = 0;
    while(i>0 && (par = (i-1)>>1) !=i) {
      if (a[par] <t) {
        a[i] = a[par];
        i = par;
      } else {
        break;
      }
    }
    a[i] = t;
  }
  //堆尾添加元素，然后执行上浮操作
  public void push(int v) {
    a[n++] = v;
    swim(n-1);
  }
  //弹出元素，然后最后一个叶结点替换根结点，下沉操作
  public int pop() {
    int ret = a[0];
    a[0] = a[--n];
    sink(0);
    return ret;
  }

  public int size() {
    return n;
  }

  public int[] getLeastNumbers(int[] arr,int k) {
    if (k<= 0 || arr ==null || arr.length ==0) {
      return new int[0];
    }
    a = new int[k+1];
    n = 0;
    for (int x: arr) {
      push(x);
      if(size() >k) {
        pop();
      }
    }
    int[] ans = new int[k];
    int i=0;
    while (size()>0) {
      ans[i++] = pop();
    }
    return ans;
  }



}
