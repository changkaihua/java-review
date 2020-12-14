package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.junit.Test;

import java.util.*;

/**
 * 652. Find Duplicate Subtrees
 *
 * @author ckh
 * @since 2020/12/2
 */
@SuppressWarnings("ALL")
public class FindDuplicateSubtree {

    /**
     * 记录所有子树以及出现的次数
     */
    HashMap<String, Integer> memo = new HashMap<>();
    /**
     * 记录重复的子树根节点
     */
    LinkedList<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    /**
     * 通过序列化判断是否重复
     */
    private String traverse(TreeNode root) {
        if (root == null) {
            return "*";
        }
        String subTree = traverse(root.left) + "," + traverse(root.right) + "," + root.val;
        int count = memo.getOrDefault(subTree, 0);
        if (count == 1) {
            res.add(root);
        }
        memo.put(subTree, count + 1);
        return subTree;
    }

    // ================== approach 2 =========================

    /**
     * uid 序号
     */
    int uidNum = 1;
    /**
     * 子树 -> uid
     */
    Map<String, Integer> trees;
    /**
     * uid -> count
     */
//    Map<Integer, Integer> count;
    List<Integer> count;
    /**
     * result
     */
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        trees = new HashMap<>();
        // count = new HashMap<>();
        count = new ArrayList<>();
        count.add(0);
        ans = new ArrayList<>();
        lookupV2(root);
        return ans;
    }

    /**
     * serial = (node.val, x, y)
     * x, y 为 左右子树的 uid
     * 如果为 null , uid = 0
     *
     * @return 该 node 的 uid
     */
    public int lookup(TreeNode node) {
        if (node == null) {
            return 0;
        }
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);

        // computeIfAbsent 避免了重复计算, 如果之前计算过的组合, 这里就相当于记忆化了??
        int uid = trees.computeIfAbsent(serial, key -> uidNum++);
//        count.put(uid, count.getOrDefault(uid, 0) + 1);

        if (uid == count.size()) {
            count.add(1);
        } else {
            count.set(uid, count.get(uid) + 1);
        }
        // 可能重复多次, 如果条件是 >1 则 ans 中出现重复的 node
        if (count.get(uid) == 2) {
            ans.add(node);
        }
        return uid;
    }

    public int lookupV2(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookupV2(node.left) + "," + lookupV2(node.right);

        int index = trees.computeIfAbsent(serial,
                key -> {
                    count.add(1);
                    return count.size() - 1;
                });
        if (count.set(index, count.get(index) + 1) == 2) ans.add(node);
        return index;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, new TreeNode(2, new TreeNode(4), null), new TreeNode(4))
        );

        List<TreeNode> duplicateSubtrees = findDuplicateSubtrees2(root);
        System.out.println("duplicateSubtrees = " + duplicateSubtrees);
    }
}
