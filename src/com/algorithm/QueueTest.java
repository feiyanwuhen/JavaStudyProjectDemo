package com.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 队列的算法题事例
 * @author hyl
 * @date 2021/10/13
 */
public class QueueTest {

  public static List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> Q = new LinkedList<>();
    if (root != null) {
      Q.offer(root);
    }
    List<List<Integer>> ans = new LinkedList<>();
    while (Q.size()>0) {
      //取出当前层里面元素个数
      final int qSize = Q.size();
      List<Integer> tmp = new LinkedList<>();
      //遍历当前层的每个结点
      for (int i=0;i < qSize; i++) {
        TreeNode cur = Q.poll();
        tmp.add(cur.val);
        if(cur.left !=null){
          Q.offer(cur.left);
        }
        if(cur.right!=null) {
          Q.offer(cur.right);
        }
      }
      ans.add(tmp);
    }
    return ans;
  }

  /**
   * 计算每一层平均值
   */
  public static List<Double> avgValue(TreeNode root) {
    Queue<TreeNode> Q = new LinkedList<>();
    if (root !=null) {
      Q.offer(root);
    }
    List<Double> result = new ArrayList<>();
    while(!Q.isEmpty()) {
      //这一层元素个数
      int qSize = Q.size();
      //保存这一层元素的和
      int sum = 0;
      for (int i=0 ;i< qSize;i++) {
        TreeNode node = Q.peek();
        sum += node.val;
        Q.poll();
        if(node.left != null) {
          Q.offer(node.left);
        }
        if(node.right !=null) {
          Q.offer(node.right);
        }
      }
      result.add((double)sum/qSize);
    }
    return result;
  }

  /**
   * 第二中解法，不用队列，用list
   */
  public List<List<Integer>> levelOrder1(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    List<TreeNode> curLevel = new ArrayList<>();
    if (root !=null) {
      curLevel.add(root);
    }
    while (curLevel.size()>0) {
      //下一层结点
      List<TreeNode> nextLevel = new ArrayList<>();
      //当前层结果
      List<Integer> curResult = new ArrayList<>();
      for(TreeNode cur :curLevel) {
        curResult.add(cur.val);
        //生成一下层
        if(cur.left !=null) {
          nextLevel.add(cur.left);
        }
        if (cur.right !=null) {
          nextLevel.add(cur.right);
        }
      }
      curLevel = nextLevel;
      ans.add(curResult);
    }
    return ans;
  }

  public Node connect(Node root) {
    Node Q = null;
    if (root !=null) {
      Q = root;
    }
    while (Q!=null) {
      //下一层前驱结点
      Node nextLevelPreNode = null;
      //下一层头结点
      Node nextLevelHead = null;
      //顺序遍历当前层每个结点
      Node curLevelNode = Q;
      while (curLevelNode !=null) {
        if (curLevelNode.left !=null) {
          if (nextLevelPreNode !=null) {
            nextLevelPreNode.next = curLevelNode.left;
          }
          nextLevelPreNode = curLevelNode.left;
          if (nextLevelHead ==null) {
            nextLevelHead = curLevelNode.left;
          }
        }
      }
      // 如果得到一个下一层的结点
      if (curLevelNode.right != null) {
        // 让下一层的前驱结点指向得到的下一层结点
        if (nextLevelPreNode != null) {
          nextLevelPreNode.next = curLevelNode.right;
        }
        nextLevelPreNode = curLevelNode.right;
        // 设置下一层的头
        if (nextLevelHead == null) {
          nextLevelHead = curLevelNode.right;
        }
      }
    }
    return Q;
  }

  static class TreeNode {
    private TreeNode(int val){
      this.val = val;
    }
    //树结点中的元素值
    int val = 0;
    //左子结点
    TreeNode left = null;
    //右子结点
    TreeNode right = null;
  }

  //多叉树层次遍历
  public static List<List<Integer>> multiTreeOrder(MultiNode root) {
    Queue<MultiNode> Q = new LinkedList<>();
    if (root != null) {
      Q.offer(root);
    }
    List<List<Integer>> ans = new LinkedList<>();
    while (Q.size()>0) {
      //取出当前层里面元素个数
      final int qSize = Q.size();
      List<Integer> tmp = new LinkedList<>();
      //遍历当前层的每个结点
      for (int i=0;i < qSize; i++) {
        MultiNode cur = Q.poll();
        tmp.add(cur.val);
          for(MultiNode multiNode :cur.children) {
            if (multiNode !=null) {
              Q.offer(multiNode);
            }
        }
      }
      ans.add(tmp);
    }
    return ans;
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(3);
    treeNode.left = new TreeNode(9);
    TreeNode right = new TreeNode(8);
    right.left = new TreeNode(6);
    right.right = new TreeNode(7);
    treeNode.right = right;
      System.out.println(levelOrder(treeNode));
      System.out.println(avgValue(treeNode));
  }
}

