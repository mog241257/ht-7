package com.mynormogue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.mynormogue.bean.BST;
import com.mynormogue.bean.Product;

public class BSTTest {
  @Test
  public void testInsertAndSearch() {
      BST<Product> bst = new BST<>();
      Product p1 = new Product("SKU1", 100.0, 90.0, "Product1", "Category1");
      Product p2 = new Product("SKU2", 200.0, 180.0, "Product2", "Category2");

      bst.insert(p1);
      bst.insert(p2);

      assertEquals(p1, bst.search(p1));
      assertEquals(p2, bst.search(p2));
      assertNull(bst.search(new Product("SKU3", 0, 0, "", "")));
  }

  @Test
  public void testInOrderTraversal() {
      BST<Product> bst = new BST<>();
      Product p1 = new Product("SKU1", 100.0, 90.0, "Product1", "Category1");
      Product p2 = new Product("SKU2", 200.0, 180.0, "Product2", "Category2");

      bst.insert(p2); // Se inserta primero el mayor
      bst.insert(p1);

      List<Product> result = bst.inOrder();
      assertEquals(p1, result.get(0)); // Verifica orden ascendente
      assertEquals(p2, result.get(1));
  }
}
