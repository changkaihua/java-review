package com.joe.leetbook.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ckh
 * @since 11/23/2020
 */
public class LowestCommonAncestor {

    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    public TreeNode lowestCommonAncestorForSortTree(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        while (true) {
            if (p.val < ans.val && q.val < ans.val) {
                ans = ans.left;
            } else if (p.val > ans.val && q.val > ans.val) {
                ans = ans.right;
            } else {
                break;
            }
        }
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        /*
            lSon : 左子树包含 p/q
            rSon : 右子树包含 p/q
         */
        boolean lSon = dfs(root.left, p, q);
        boolean rSon = dfs(root.right, p, q);
        if ((lSon && rSon) || ((root.val == p.val || root.val == q.val) && (lSon || rSon))) {
            res = root;
        }
        return lSon || rSon || (root.val == p.val || root.val == q.val);
    }


    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestorMethod2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    @Test
    public void test() {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3,
                new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                new TreeNode(1, new TreeNode(0), new TreeNode(8)));

        TreeNode treeNode = lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4));

        System.out.println(treeNode);
    }


}
