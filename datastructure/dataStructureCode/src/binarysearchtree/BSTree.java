package binarysearchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BSTree {
    BNode root;

    public BSTree(int val) {
        this.root = new BNode(val);
    }

    public static BSTree newBSTree(int[] arr) {
        if (arr.length == 0) {
            return new BSTree(0);
        }
        BSTree bsTree = new BSTree(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            bsTree.insert(new BNode(arr[i]));
        }
        return bsTree;
    }

    public void insert(BNode node) {
        traverseInsert(root, node);
    }

    private void traverseInsert(BNode node, BNode inserted) {
        if (node.val > inserted.val) {
            if (node.left != null) {
                traverseInsert(node.left, inserted);
            } else {
                node.left = inserted;
                return;
            }
        } else {
            if (node.right != null) {
                traverseInsert(node.right, inserted);
            } else {
                node.right = inserted;
                return;
            }
        }
    }

    public boolean search(int target) {
        return traverseSearch(root, target);
    }

    private boolean traverseSearch(BNode node, int target) {
        if (node.val == target) {
            return true;
        }
        if (node.val > target) {
            if (node.left == null) {
                return false;
            } else {
                return traverseSearch(node.left, target);
            }
        } else {
            if (node.right == null) {
                return false;
            } else {
                return traverseSearch(node.right, target);
            }
        }


    }

    public String traverse() {
        StringBuilder sb = new StringBuilder();
        midTraverse(this.root, sb);
        return sb.toString();
    }

    private void midTraverse(BNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        midTraverse(node.left, result);
        result.append(node.val);
        midTraverse(node.right, result);
    }

    public boolean remove(int val) {
		int[] arr = new int[2];
		Arrays.stream(arr).boxed().collect(Collectors.toList());
		arr.s
		List<int[]> ints = Arrays.asList(arr);
	}
}

class BNode {
    int val;
    BNode left;
    BNode right;

    public BNode(int val) {
        this.val = val;
    }
}
