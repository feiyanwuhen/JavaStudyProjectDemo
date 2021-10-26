package com.algorithm;

/**
 * @author hyl
 * @date 2021/10/23
 */
public class LinkListTest {

  private ListNode dummy = new ListNode();
  private ListNode tail = dummy;
  private int length = 0;

  public LinkListTest() {

  }


  public ListNode reverseList(ListNode head) {
    ListNode dummy = new ListNode();
    while (head !=null) {
      ListNode tmp = head.next;
      head.next = dummy.next;
      dummy.next = head;
      head = tmp;
    }
    return dummy.next;
  }

  public ListNode removeNode(ListNode head,int val) {
    ListNode dummy = new ListNode();
    ListNode tail = dummy;
    ListNode p = head;
    while ( p!=null) {
      ListNode back = p.next;
      if (p.val != val) {
        tail.next = p;
        tail = p;
      }
      p = back;
    }
    tail.next = null;
    return dummy.next;
  }

  public ListNode removeDuplicateNode(ListNode head) {
    ListNode dummy = new ListNode();
    ListNode tail = dummy;
    ListNode p = head;
    while ( p !=null) {
      ListNode back = p.next;
      if (tail == dummy || tail.val !=p.val) {
        tail.next = p;
        tail = p;
      }
      p = back;
    }
    tail.next = null;
    return dummy.next;
  }




  public void addAtTail(int val) {
    tail.next = new ListNode(val);
    tail = tail.next;
    length++;
  }

  public void addAtHead(int val) {
    ListNode head = new ListNode(val);
    head.next = dummy.next;
    dummy.next = head;
    if (tail == dummy) {
      tail = head;
    }
    length++;
  }

  /**
   * 不会空指针？
   */
  public int get(int index) {
    if (index < 0 || index >= length) {
      return -1;
    }
    return getPrevNode(index).next.val;
  }

  public ListNode getPrevNode(int index) {
    ListNode front = dummy.next;
    ListNode back = dummy;
    for (int i = 0; i < index && front != null; i++) {
      back = front;
      front = front.next;
    }
    return back;
  }

  public void addAtIndex(int index, int val) {
    if (index > length) {
      // Case 1.如果 index 大于链表长度，则不会插入结点。
      return;
    } else if (index == length) {
      // Case 2.如果 index 等于链表的长度，则该结点将附加到链表的末尾。
      addAtTail(val);
    } else if (index <= 0) {
      // Case 3. 如果index小于0，则在头部插入结点。
      addAtHead(val);
    } else {
      // Case 4.
      // 得到index之前的结点pre
      ListNode pre = getPrevNode(index);
      // 在pre的后面添加新结点
      ListNode p = new ListNode(val);
      p.next = pre.next;
      pre.next = p;
      // 注意修改长度
      length++;
    }
  }

  public void deleteAtIndex(int index) {
    if (index>=length || index<0) {
      return;
    }

    ListNode preNode = getPrevNode(index);
    if (tail == preNode.next) {
      tail = preNode;
    }
    ListNode q = preNode.next;
    preNode.next = q.next;
    q.next =null;
    length--;

  }

  class ListNode {

    public int val = 0;
    public ListNode next = null;

    public ListNode() {
    }

    public ListNode(int x) {
      val = x;
    }
  }
}
