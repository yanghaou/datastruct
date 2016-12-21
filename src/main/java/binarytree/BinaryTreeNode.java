package binarytree;

/**
 * function 二叉树的一个节点
 * Author: yang.hao
 * Date: 2016/12/20
 */
public class BinaryTreeNode<T extends Comparable<T>> {
    //左子树
    BinaryTreeNode<T> leftChild;
    //右子树
    BinaryTreeNode<T> rightChild;
    //数据
    T data;

    public BinaryTreeNode(BinaryTreeNode<T> leftChild, BinaryTreeNode<T> rightChild, T data) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public boolean isLeaf(){
        if (leftChild ==null && rightChild == null)
            return true;
        else return false;
    }
}
