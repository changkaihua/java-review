package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.TimeUnit;

/**
 * @author ckh
 * @since 2021/1/21
 */
public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

}
