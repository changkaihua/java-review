package com.joe.leetbook.tree;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author ckh
 * @create 11/6/20 2:44 PM
 */
public class TreeSummary {

    HashMap<Integer, Integer> infixDirectory = new HashMap<>();
    int[] temp;

    public TreeNode postBuildTree(int[] inorder, int[] postorder) {
        temp = postorder;
        infixDirectory = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            infixDirectory.put(inorder[i], i);
        }
        return postRecur(postorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode postRecur(int pRootIdx, int inLeftIdx, int inRightIdx) {
        if (inLeftIdx > inRightIdx) return null;

        TreeNode root = new TreeNode(temp[pRootIdx]);
        int indexOfRootInInorder = infixDirectory.get(temp[pRootIdx]);

        root.left = postRecur(pRootIdx - (inRightIdx - indexOfRootInInorder) - 1,
                inLeftIdx,
                indexOfRootInInorder - 1);

        root.right = postRecur(pRootIdx - 1, indexOfRootInInorder + 1, inRightIdx);
        return root;
    }


    public TreeNode preBuildTree(int[] preorder, int[] inorder) {
        temp = preorder;
        infixDirectory = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            infixDirectory.put(inorder[i], i);
        }
        return preRecur(0, 0, inorder.length - 1);

    }

    private TreeNode preRecur(int preRootIdx, int inLeftIdx, int inRightIdx) {
        if (inLeftIdx > inRightIdx) return null;
        TreeNode root = new TreeNode(temp[preRootIdx]);
        int idxOfInorder = infixDirectory.get(temp[preRootIdx]);

        root.left = preRecur(preRootIdx + 1,
                inLeftIdx,
                idxOfInorder - 1);

        root.right = preRecur(preRootIdx + 1 + idxOfInorder - inLeftIdx,
                idxOfInorder + 1,
                inRightIdx);

        return root;
    }

    @Test
    public void test() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};

        TreeNode treeNode = postBuildTree(inorder, postOrder);

    }
}
