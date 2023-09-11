package org.example.tree;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import java.util.*;

/**
 * @author LiJinLong
 * @description
 * @create 2023-09-06 23:14
 * @date 1.0
 */
public class Demo1 {

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};