/**
 * @author Julian Beaulieu
 * @git julianbeaulieu
 * @version 2.0
 * @description JUnit test for BinaryTree class
 */
package edu.miracosta.cs113;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * BinaryTreeTest.java
 * Class containing JUnit test methods for the Binary Tree data structure
 *
 * @version 1.0
 * @author Julian Beaulieu @ Reesha Rajen
 */

public class BinaryTreeTest {

    /** CONSTANTS */
    private static final BinaryTree.Node ROOT_NODE = new BinaryTree.Node("Cow");
    private static final BinaryTree.Node LEFT_NODE = new BinaryTree.Node("Chipmunk");
    private static final BinaryTree.Node RIGHT_NODE = new BinaryTree.Node("Dog");
    private static final BinaryTree.Node[] NODES = {ROOT_NODE, LEFT_NODE, RIGHT_NODE};

    /**
     * Tests for correct construction of Binary Tree
     */
    @Test
    public void testConstructTree() {
        BinaryTree tree = new BinaryTree();
        assertNull("Expected and actual trees DON'T match", tree.root);
    }

    /**
     * Tests for correct construction of Binary Tree with root
     */
    @Test
    public void testConstructTreeWithRoot() {

        for (BinaryTree.Node n : NODES) {
            BinaryTree test = new BinaryTree(n);
            assertEquals("Expected and actual roots DON'T MATCH", n, test.root);
        }
    }

    /**
     * Tests for correct retrieval of the left and right subtrees
     */
    @Test
    public void testGetLeftRightSubtree() {
        String expectedL, actualL, expectedR, actualR;
        BinaryTree leftSubtree = new BinaryTree(LEFT_NODE);
        BinaryTree rightSubtree = new BinaryTree(RIGHT_NODE);

        BinaryTree testLeft = new BinaryTree(ROOT_NODE, leftSubtree, null);
        BinaryTree testRight = new BinaryTree(ROOT_NODE, null, rightSubtree);

        expectedL = leftSubtree.toString();
        expectedR = rightSubtree.toString();
        actualL = testLeft.getLeftSubtree().toString();
        actualR = testRight.getRightSubtree().toString();

        assertEquals("Expected and actual subtrees DON'T MATCH", expectedL, actualL);
        assertEquals("Expected and actual subtrees DON'T MATCH", expectedR, actualR);
    }
}
