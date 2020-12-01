package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees2
 *
 * @author ckh
 * @since 12/1/2020
 */
public class UniqueBinarySearchTrees {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return helper(1, n);
    }

    /**
     * 二叉搜索树
     * 1, ..., i, ..., n
     * 如果 根 为 i, 则 左子树为 1 ~ i-1, 右子树 为 i+1 ~ n
     * <p>
     * 使用递归
     */
    public List<TreeNode> helper(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            // 这句很关键, 保证叶子节点的初始化
            // 要正确构建所有树，依赖于对左右子树列表的遍历，也就是下面代码两层for-each的地方
            // 如果其中一个列表为空，那么循环都将无法进行, 所以添加 null
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = helper(start, i - 1);
            List<TreeNode> rightTrees = helper(i + 1, end);

            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode currRoot = new TreeNode(i);
                    currRoot.left = leftTree;
                    currRoot.right = rightTree;
                    allTrees.add(currRoot);
                }
            }
        }

        return allTrees;
    }

    @Test
    public void test() {
        List<TreeNode> treeNodes = generateTrees(3);

        System.out.println(treeNodes);
    }
}
