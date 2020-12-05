package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

/**
 * 617. Merge Two Binary
 * @author ckh
 * @since 2020/12/5
 */
public class MergeTree {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null || t2 == null) {
            return t1 == null ? t2 : t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

}
