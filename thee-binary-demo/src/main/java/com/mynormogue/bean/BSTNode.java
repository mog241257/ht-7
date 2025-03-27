package com.mynormogue.bean;

public class BSTNode<E extends Comparable<E>> {
  E data;
  BSTNode<E> left;
  BSTNode<E> right;

  public BSTNode(E data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }
}
