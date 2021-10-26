package com.algorithm;

import java.util.List;

/**
 * @author hyl
 * @date 2021/10/14
 */
public class MultiNode {

  public int val;

  public List<MultiNode> children;

  public MultiNode(){}

  public MultiNode(int val,List<MultiNode> children) {
    this.val = val;
    this.children = children;
  }

}
