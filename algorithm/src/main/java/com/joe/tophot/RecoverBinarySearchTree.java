package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import com.sun.media.sound.RIFFInvalidDataException;
import org.junit.Test;

import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 99. Recover Binary Search Tree
 *
 * @author ckh
 * @since 2020/12/4
 */
public class RecoverBinarySearchTree {
    /**
     * O(n) space
     */
    public void recoverTreeV1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int x = -1, y = -1;
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i + 1) < list.get(i)) {
                y = list.get(i + 1);
                if (x == -1) {
                    x = list.get(i);
                } else {
                    break;
                }
            }
        }

        swapTwoNodeVal(root, 2, x, y);
    }

    private void swapTwoNodeVal(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;

                if (--count == 0) return;
            }
            swapTwoNodeVal(root.right, count, x, y);
            swapTwoNodeVal(root.left, count, x, y);
        }
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }


    private TreeNode wrong1, wrong2, prior;

    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = wrong1.val;
        wrong1.val = wrong2.val;
        wrong2.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);

        if (null != prior && prior.val > root.val) {
            if (wrong1 == null) {
                wrong1 = prior;
            }
            wrong2 = root;
        }
        /*
            a b c : a, b相邻,需要交换,
            a...b c: a,b不相邻,要交换,
            只要 a < c, 那么从 c后就不用判断了, 因为只有2个点错误, 后面都是正确的

            1   3   2    4    6
               w1  pri  root
                   w2

            2   8   7   6   14  15  18
               w1      pri  root
                       w2

            也就是说, 当 wrong2 位置固定后, 然后判断其后的节点与 wrong1 的值大小
         */
        if (prior != null && wrong2 == prior && wrong1.val < root.val) {
            return;
        }
        prior = root;
        inorder(root.right);
    }


    @Test
    public void test() {
//        TreeNode root = new TreeNode(3, new TreeNode(1),
//                new TreeNode(4, new TreeNode(2), new TreeNode(6)));

        TreeNode root = new TreeNode(6,
                new TreeNode(8, new TreeNode(2), new TreeNode(7)),
                new TreeNode(15, new TreeNode(14), new TreeNode(18)));


        recoverTree(root);

    }


}
