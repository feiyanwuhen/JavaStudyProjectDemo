package com.study.thread;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ThreadTest {
  static class TreeNode//节点结构
  {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value)
    {
      this.value = value;
    }
  }
  public static void main(String[] args) {



    TreeNode[] node = new TreeNode[10];//以数组形式生成一棵完全二叉树
    for(int i = 0; i < 10; i++)
    {
      node[i] = new TreeNode(i);
    }
    for(int i = 0; i < 10; i++)
    {
      if(i*2+1 < 10)
        node[i].left = node[i*2+1];
      if(i*2+2 < 10)
        node[i].right = node[i*2+2];
    }

    System.out.println(inorder(node[0]));
  }

  public static List<Integer> inorder(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.peek();
      while (node != null) {
        stack.push(node.left);
        node = node.left;
      }
      stack.pop();
      if (!stack.isEmpty()) {
        node = stack.pop();
        ans.add(node.value);
        stack.push(node.right);
      }
    }
    return ans;
  }



}
