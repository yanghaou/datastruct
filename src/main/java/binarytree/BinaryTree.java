package binarytree;

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
     * 插入一个节点
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

    public boolean delete(T data){
        boolean flag = false;

        return flag;
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
     * 输出当前节点
     * @param current
     */
    public void visit(BinaryTreeNode<T> current) {
        if(current != null && current.data!=null){
            System.out.println(current.data);
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>();
        int s[] = {9,3,16,7,12,8};
        for (int i = 0; i < s.length; i++) {
            tree.insert(s[i]);
        }


//        System.out.println(tree.getMax() +"  "+tree.getMin());
//        System.out.println(tree.searchParent(8).data);
//        System.out.println(tree.search(3).leftChild +"  "+tree.search(3).rightChild.data);

//        System.out.println("二叉树节点个数:"+tree.getSize());
//        System.out.println("-----------下面是先序遍历二叉树--------------");
        //tree.preOrder(tree.root);
//        System.out.println("-----------下面是中序遍历二叉树--------------");
        //tree.inOrder(tree.root);
//        System.out.println("-----------下面是后序遍历二叉树--------------");
        //tree.postOrder(tree.root);
    }

}
