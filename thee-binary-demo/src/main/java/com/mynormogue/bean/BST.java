package com.mynormogue.bean;

import java.util.ArrayList;
import java.util.List;

public class BST<E extends Comparable<E>> {
  private BSTNode<E> root;

  public BST() {
      this.root = null;
  }

  // Insertar un elemento
  public void insert(E data) {
      root = insertRec(root, data);
  }

  private BSTNode<E> insertRec(BSTNode<E> node, E data) {
      if (node == null) {
          return new BSTNode<>(data);
      }
      if (data.compareTo(node.data) < 0) {
          node.left = insertRec(node.left, data);
      } else if (data.compareTo(node.data) > 0) {
          node.right = insertRec(node.right, data);
      }
      return node;
  }

  // Buscar un elemento
  public E search(E data) {
      return searchRec(root, data);
  }

  private E searchRec(BSTNode<E> node, E data) {
      if (node == null) {
          return null;
      }
      if (data.compareTo(node.data) == 0) {
          return node.data;
      }
      return data.compareTo(node.data) < 0 
          ? searchRec(node.left, data) 
          : searchRec(node.right, data);
  }

  // Recorrido In Order (ascendente)
  public List<E> inOrder() {
      List<E> result = new ArrayList<>();
      inOrderRec(root, result);
      return result;
  }

  private void inOrderRec(BSTNode<E> node, List<E> result) {
      if (node != null) {
          inOrderRec(node.left, result);
          result.add(node.data);
          inOrderRec(node.right, result);
      }
  }

  // Recorrido In Order inverso (descendente)
  public List<E> inOrderDesc() {
      List<E> result = new ArrayList<>();
      inOrderDescRec(root, result);
      return result;
  }

  private void inOrderDescRec(BSTNode<E> node, List<E> result) {
      if (node != null) {
          inOrderDescRec(node.right, result);
          result.add(node.data);
          inOrderDescRec(node.left, result);
      }
  }
}
