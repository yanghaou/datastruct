package binarytree;

import java.util.ArrayDeque;

/**
 * function 二叉树操作
 * Author: yang.hao
 * Date: 2016/12/20
 */
public class BinaryTree<T extends Comparable<T>> {
    private int size;
    private BinaryTreeNode <T> root;

    //构建一颗空树
    public BinaryTree(){
        root = null;
        size =0;
    }

    //节点个数
    public int getSize() {
        return size;
    }

    /**
     * 插入一个节点,非递归
     * @param data
     */
    public void insert(T data){
        BinaryTreeNode node = new BinaryTreeNode<T>(null,null,data);

        //如果根节点为空
        if (root ==null){
            root = node;
        }else {
            BinaryTreeNode<T> current = root;
            BinaryTreeNode<T> parent ;
            while (true){
                parent = current;
                if (data.compareTo(current.data)<0){
                    current = current.leftChild;
                    if (current == null){
                        parent.leftChild = node;
                        break;
                    }
                }else {
                    current = current.rightChild;
                    if (current == null){
                        parent.rightChild = node;
                        break;
                    }
                }
            }
        }
        size++;
    }

    /**递归插入*/
    public void dg_insert(T data){
        root = insert(root,data);
    }
    public BinaryTreeNode<T> insert(BinaryTreeNode<T> node,T data){
        if (node == null){
            node = new BinaryTreeNode<T>(null,null,data);
        }else {
            int result = data.compareTo(node.data);
            if ( result < 0){
                node.leftChild = insert(node.leftChild,data);
            }else {
                node.rightChild = insert(node.rightChild,data);
            }
        }
        return node;
    }

    /**
     * 查找元素
     * @param data
     * @return
     */
    public BinaryTreeNode<T> search(T data){
        BinaryTreeNode<T> node = root;
        if (node == null) return null;
        while (node !=null && data.compareTo(node.data) !=0){
           if (data.compareTo(node.data)<0){
               node = node.leftChild;
           }else {
               node = node.rightChild;
           }
        }
        return node;
    }

    /**递归查找*/
    public boolean contains(T data){
        return contains(data,root);
    }

    private boolean contains(T data, BinaryTreeNode<T> node) {
        if (node == null) return false;
        int result = data.compareTo(node.data);
        if (result > 0){
            return contains(data,node.rightChild);
        }else if (result < 0){
            return contains(data,node.leftChild);
        }else return true;
    }

    /**
     * 查找父节点
     * @param data
     * @return
     */
    public BinaryTreeNode<T> searchParent(T data){
        BinaryTreeNode<T> parent = null;
        BinaryTreeNode<T> node = root;
        if (root == null) return null;
        while (node.data.compareTo(data) != 0){
            parent = node;
            if (data.compareTo(node.data) <0){
                node = node.leftChild;
            }else {
                node = node.rightChild;
            }
        }
        return parent;
    }

    /**查询出最小元素所在的结点*/
    public BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        if(node==null)
            return null;
        else if(node.leftChild==null)
            return node;
        return findMin(node.leftChild);//递归查找
    }

    /**查询出最大元素所在的结点*/
    public BinaryTreeNode<T> findMax(BinaryTreeNode<T> node) {
        if(node!=null)
        {
            while(node.rightChild!=null)
                node=node.rightChild;
        }
        return node;
    }

    /**
     * 删除节点
     * @param data
     * @return
     */
    public void delete(T data){
        root = delete(data,root);
    }

    /**递归删除*/
    private BinaryTreeNode<T> delete(T data, BinaryTreeNode<T> node) {
        //没有找到 do nothing
        if (node == null)
            return node;
        int result = data.compareTo(node.data);
        if (result > 0 ){
            node.rightChild = delete(data,node.rightChild);
        }else if (result < 0){
            node.leftChild = delete(data,node.leftChild);
        }else if (node.leftChild !=null && node.rightChild !=null){
            node.data = findMin(node.rightChild).data;
            node.rightChild = delete(node.data,node.rightChild);
        }else node = (node.leftChild !=null) ?node.leftChild:node.rightChild;
        return node;
    }

    /**
     * 获取根节点
     * @return
     */
    public BinaryTreeNode<T> getRoot(){
        return root;
    }

    /**
     * 获取最大值
     * @return
     */
    public T getMax(){
        BinaryTreeNode<T> node = root;
        if (root == null)return null;
        while (node.rightChild !=null){
            node = node.rightChild;
        }
        return node.data;
    }

    /**
     * 获取最小值
     * @return
     */
    public T getMin(){
        BinaryTreeNode<T> node = root;
        if (root == null) return null;
        while (node.leftChild !=null){
            node = node.leftChild;
        }
        return node.data;
    }




    /**
     * 前序遍历
     * @param root
     */
    public void preOrder(BinaryTreeNode<T> root) {
        if (root !=null) {
            visit(root);
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inOrder(BinaryTreeNode<T> root) {
        if (root !=null) {
            inOrder(root.leftChild);
            visit(root);
            inOrder(root.rightChild);
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void postOrder(BinaryTreeNode<T> root) {
        if (root !=null) {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            visit(root);
        }
    }

    /**
     * 深度遍历
     */
    public void depthOrderTraversal(){
        if (root == null){
            System.out.println("empty tree");
            return;
        }
        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<BinaryTreeNode>();
        stack.push(root);
        while (stack.isEmpty() == false){
            BinaryTreeNode node = stack.pop();
            System.out.println(node.data+"    ");

            if (node.rightChild !=null){
                stack.push(node.rightChild);
            }
            if (node.leftChild !=null){
                stack.push(node.leftChild);
            }
        }

        System.out.println("遍历完成 \n");
    }

    /**
     * 广度遍历
     */
    public void levelOrderTraversal(){
        if (root == null){
            System.out.println("empty tree!");
            return;
        }
        ArrayDeque<BinaryTreeNode> deque = new ArrayDeque<BinaryTreeNode>();
        deque.add(root);

        while (deque.isEmpty() == false){
            BinaryTreeNode node = deque.remove();
            System.out.println(node.data +"    ");

            if (node.leftChild !=null){
                deque.add(node.leftChild);
            }

            if (node.rightChild !=null){
                deque.add(node.rightChild);
            }
        }
        System.out.println("遍历完成 \n");
    }

    /**
     * 输出当前节点
     * @param current
     */
    public void visit(BinaryTreeNode<T> current) {
        if(current != null && current.data!=null){
            System.out.println(current.data);
        }
    }

    /**二叉树是否为空*/
    public boolean isEmpty(){
        return root == null;
    }
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        int s[] = {9,3,16,7,12,8,9};
        for (int i = 0; i < s.length; i++) {
            //tree.insert(s[i]);
            tree.dg_insert(s[i]);
            //tree.insert(tree.root,s[i]);
        }

//        tree.delete(9);

        tree.depthOrderTraversal();
        tree.levelOrderTraversal();
//        System.out.println(tree.getMax() +"  "+tree.getMin());
//        System.out.println(tree.searchParent(8).data);
//        System.out.println(tree.search(3).leftChild +"  "+tree.search(3).rightChild.data);

//        System.out.println("二叉树节点个数:"+tree.getSize());
//        System.out.println("-----------下面是先序遍历二叉树--------------");
//        tree.preOrder(tree.root);
//        System.out.println("-----------下面是中序遍历二叉树--------------");
        //tree.inOrder(tree.root);
//        System.out.println("-----------下面是后序遍历二叉树--------------");
        //tree.postOrder(tree.root);
    }

}
