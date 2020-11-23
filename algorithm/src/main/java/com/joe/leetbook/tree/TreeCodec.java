package com.joe.leetbook.tree;

import org.junit.Test;

import javax.lang.model.element.VariableElement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your Codec object will be instantiated and called as such:
 * Codec ser = new Codec();
 * Codec deser = new Codec();
 * TreeNode ans = deser.deserialize(ser.serialize(root));
 * 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的
 *
 * @author ckh
 * @since 11/23/2020
 */
public class TreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        StringBuilder res = new StringBuilder();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode != null) {
                res.append(curNode.val).append(",");
                queue.offer(curNode.left);
                queue.offer(curNode.right);
            } else {
                res.append("null,");
            }
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        String[] val = data.split(",");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(val[0]));
        int cur = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (!"null".equals(val[cur])) {
                curNode.left = new TreeNode(Integer.parseInt(val[cur]));
                queue.offer(curNode.left);
            }
            cur++;
            if (!"null".equals(val[cur])) {
                curNode.right = new TreeNode(Integer.parseInt(val[cur]));
                queue.offer(curNode.right);
            }
            cur++;
        }
        return root;
    }

    @Test
    public void test() {
        // [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode root = new TreeNode(3,
                new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                new TreeNode(1, new TreeNode(0), new TreeNode(8)));

        System.out.println(serialize(root));

        TreeNode deserialize = deserialize(serialize(root));
        TreeTraverse traverse = new TreeTraverse();
        System.out.println(traverse.levelOrder(deserialize));
    }
}
